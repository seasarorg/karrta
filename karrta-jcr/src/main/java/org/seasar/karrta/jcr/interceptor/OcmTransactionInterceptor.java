/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.karrta.jcr.interceptor;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.api.XASession;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.karrta.jcr.session.JcrSessionFactory;
import org.seasar.karrta.jcr.session.JcrSessionManager;

/**
 * OCM Interceptor.
 * 
 * @author yosukehara
 * 
 */
public class OcmTransactionInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private static final Log logger_ = LogFactory.getLog(OcmTransactionInterceptor.class);

    /** session factory */
    private JcrSessionFactory sessionFactory_;

    public void setJcrSessionFactory(JcrSessionFactory sessionFactory) {
        this.sessionFactory_ = sessionFactory;
    }

    /** session manager */
    private JcrSessionManager sessionManager_;

    public void setJcrSessionManager(JcrSessionManager sessionManager) {
        this.sessionManager_ = sessionManager;
    }

    /*
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        int hashCode = Thread.currentThread().hashCode();
        logger_.debug("::: [Begin Transaction] ::: [" + hashCode + "] :::");

        XASession session = (XASession) this.sessionFactory_.getSession();
        this.sessionManager_.addSession(hashCode, session);

        Xid xid = new Xid() {
            public byte[] getBranchQualifier() {
                return new byte[0];
            }

            public int getFormatId() {
                return 0;
            }

            public byte[] getGlobalTransactionId() {
                return new byte[0];
            }
        };
        XAResource xares = session.getXAResource();
        xares.start(xid, XAResource.TMNOFLAGS);

        logger_.debug(xares);

        Object result = null;
        try {
            result = invocation.proceed();

            xares.end(xid, XAResource.TMSUCCESS);
            xares.prepare(xid);
            xares.commit(xid, false);

        } catch (Exception e) {
            xares.rollback(xid);

        } finally {
            session.logout();
            this.sessionManager_.removeSession(hashCode, session);

            logger_.debug("::: [End Transaction] ::: [" + hashCode + "] :::");
        }
        return result;
    }

}
