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
package org.seasar.karrta.jcr.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.jcr.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * jcr session manager.
 * 
 * @author yosukehara
 * 
 */
public class JcrSessionManager extends GenericObjectPool {
    private static final Log logger_ = LogFactory.getLog(JcrSessionManager.class);

    /** sessions */
    private ConcurrentMap<Integer, Session> sessions_ = new ConcurrentHashMap<Integer, Session>();

    /**
     * @param sessionFactory
     * @param config
     */
    public JcrSessionManager(JcrSessionFactory sessionFactory, GenericObjectPool.Config config) {
        super(sessionFactory, config);
        
        logger_.debug("                 maxActive:[" + config.maxActive                  + "]");
        logger_.debug("                   maxIdle:[" + config.maxIdle                    + "]");
        logger_.debug("                   maxWait:[" + config.maxWait                    + "]");
        logger_.debug("       whenExhaustedAction:[" + config.whenExhaustedAction        + "]");
        logger_.debug("minEvictableIdleTimeMillis:[" + config.minEvictableIdleTimeMillis + "]");
    }

    /**
     * add session.
     * 
     * @param currentThreadHashCode
     * @param session
     */
    public void addSession(int currentThreadHashCode, Session session) {
        this.sessions_.put(new Integer(currentThreadHashCode), session);
    }

    /**
     * remove session.
     * 
     * @param currentThreadHashCode
     * @param session
     */
    public void returnSession(int currentThreadHashCode, Session session) {
        this.sessions_.remove(new Integer(currentThreadHashCode), session);
        try {
            super.returnObject(session);
        } catch (Exception e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }

    /*
     * @see org.apache.commons.pool.impl.GenericObjectPool#borrowObject()
     */
    public Object borrowObject() {
        return this.borrowObject(Thread.currentThread().hashCode());
    }
    
    /**
     * borrow object.
     * 
     * @param currentThreadHashCode
     * @return
     */
    public Object borrowObject(int currentThreadHashCode) {
        return this.getSession(currentThreadHashCode);
    }
    
    /**
     * get session.
     * 
     * @param currentThreadHashCode
     */
    private Session getSession(int currentThreadHashCode) {
        Session session = this.sessions_.get(new Integer(currentThreadHashCode));
        if (session == null) {
            try {
                session = (Session) super.borrowObject();
                this.sessions_.put(new Integer(currentThreadHashCode), session);

            } catch (Exception e) {
                throw new JcrRepositoryRuntimeException("", e);
            }
            logger_.debug("::: JcrSessionManager-session:[" + session + "] :::");
        }
        return session;
    }

    /**
     * 
     * @param currentThreadHashCode
     * @return
     */
    public boolean isExist(int currentThreadHashCode) {
        return (this.sessions_.get(new Integer(currentThreadHashCode)) != null);
    }

    /**
     * clear.
     */
    public void destroy() {
        this.sessions_.clear();
    }

}
