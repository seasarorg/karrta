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
package org.seasar.karrta.jcr.intercepter;

import java.lang.reflect.Method;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.api.XASession;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.karrta.jcr.ocm.ObjectContentManagerFactory;
import org.seasar.karrta.jcr.session.JcrSessionManager;

/**
 * OCM Intercepter.
 * 
 * @author yosukehara
 * 
 */
public class JcrTransactionInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private static final Log logger_ = LogFactory.getLog(JcrTransactionInterceptor.class);

    /** session manager */
    private JcrSessionManager sessionManager_;

    public void setJcrSessionManager(JcrSessionManager sessionManager) {
        this.sessionManager_ = sessionManager;
    }

    /** object content manager */
    private ObjectContentManagerFactory ocmFactory;

    public void setOcmFactory(ObjectContentManagerFactory ocmFactory) {
        this.ocmFactory = ocmFactory;
    }

    /*
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Thread currentThread = Thread.currentThread();
        if (this.sessionManager_.isExist(currentThread)) {
            return invocation.proceed();
        }

        logger_.debug("::: [Begin Transaction] ::: [" + currentThread + "] :::");

        // check in jcr-session.
        XASession session = (XASession) sessionManager_.borrowObject(currentThread);
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

        Object result = null;
        try {
            for (Method m : invocation.getThis().getClass().getMethods()) {
                if ("setOcmQueryManager".equals(m.getName())) {
                    m.invoke(invocation.getThis(), this.ocmFactory.getQueryManager());
                }
                if ("setQueryManager".equals(m.getName())) {
                    m.invoke(invocation.getThis(), session.getWorkspace().getQueryManager());
                }
            }
            result = invocation.proceed();

            xares.end(xid, XAResource.TMSUCCESS);
            xares.prepare(xid);
            xares.commit(xid, false);

        } catch (Exception e) {
            e.printStackTrace();
            xares.rollback(xid);

        } finally {
            // check out jcr-session.
            this.sessionManager_.returnSession(currentThread, session);
            logger_.debug("::: [End Transaction] ::: [" + currentThread + "] :::");
        }
        return result;
    }

}
