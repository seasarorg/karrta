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
    private ConcurrentMap<Thread, Session> sessions_ = new ConcurrentHashMap<Thread, Session>();

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
     * @param thread
     * @param session
     */
    public void addSession(Thread thread, Session session) {
        this.sessions_.put(thread, session);
    }

    /**
     * remove session.
     * 
     * @param thread
     * @param session
     */
    public void returnSession(Thread thread, Session session) {
        this.sessions_.remove(thread, session);
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
        return this.borrowObject(Thread.currentThread());
    }
    
    /**
     * borrow object.
     * 
     * @param currentThreadHashCode
     * @return
     */
    public Object borrowObject(Thread thread) {
        return this.getSession(thread);
    }
    
    /**
     * get session.
     * 
     * @param thread
     */
    private Session getSession(Thread thread) {
        Session session = this.sessions_.get(thread);
        if (session != null) return session;

        try {
            session = (Session) super.borrowObject();
            this.sessions_.put(thread, session);
            logger_.debug("::: JcrSessionManager-session:[" + session + "] :::");
            return session;
        } catch (Exception e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }

    /**
     * 
     * @param currentThreadHashCode
     * @return
     */
    public boolean isExist(Thread thread) {
        return this.sessions_.containsKey(thread);
        //return (this.sessions_.get(new Integer(currentThreadHashCode)) != null);
    }

    /**
     * clear.
     */
    public void destroy() {
        this.sessions_.clear();
    }

}
