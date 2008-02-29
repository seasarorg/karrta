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

/**
 * jcr session manager.
 * 
 * @author yosukehara
 *
 */
public class JcrSessionManager {
    /** sessions */
    private ConcurrentMap<Integer, Session> sessions_ = new ConcurrentHashMap<Integer, Session>();
    
    /** session factory */
    private JcrSessionFactory sessionFactory_;
    
    public void setJcrSessionFactory(JcrSessionFactory sessionFactory) {
        this.sessionFactory_ = sessionFactory;
    }
    
    /**
     * add session.
     * 
     * @param hashCode
     * @param session
     */
    public void addSession(int hashCode, Session session) {
        this.sessions_.put(new Integer(hashCode), session);
    }
    
    /**
     * remove session.
     * 
     * @param hashCode
     * @param session
     */
    public void removeSession(int hashCode, Session session) {
        this.sessions_.remove(new Integer(hashCode), session);
    }
    
    /**
     * get session.
     * 
     * @return
     */
    public Session getSession() {
        return this.sessionFactory_.getSession();
    }
    
    /**
     * get session.
     * 
     * @param hashCode
     */
    public Session getSession(int hashCode) {
        Session session = this.sessions_.get(new Integer(hashCode));
        if (session == null) {
            session = this.getSession();
        }
        return session;
    }
    
    /**
     * clear.
     */
    public void destroy() {
        this.sessions_.clear();
    }
    
}
