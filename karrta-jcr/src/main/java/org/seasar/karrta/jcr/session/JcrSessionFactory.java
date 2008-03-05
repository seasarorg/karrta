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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Workspace;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeTypeManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.core.nodetype.InvalidNodeTypeDefException;
import org.apache.jackrabbit.core.nodetype.NodeTypeDef;
import org.apache.jackrabbit.core.nodetype.NodeTypeManagerImpl;
import org.apache.jackrabbit.core.nodetype.NodeTypeRegistry;
import org.apache.jackrabbit.core.nodetype.xml.NodeTypeReader;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;
import org.seasar.karrta.jcr.observation.EventListenerDefinition;
import org.seasar.karrta.jcr.register.JcrObservationComponentRegister;

/**
 * 
 * @author yosuke
 * 
 */
public class JcrSessionFactory {
    private static final Log logger_ = LogFactory.getLog(JcrSessionFactory.class);

    /** default workspace name */
    private static final String DEFAULT_WORKSPACE_NAME = "default";

    /** default user */
    private static final String DEFAULT_USER = "defaultuser";

    /** default password */
    private static final String DEFAULT_PASSWORD = "defaultpassword";

    public JcrSessionFactory() {
    }

    /**
     * @param repository
     * @param workspaceName
     * @param credentials
     */
    public JcrSessionFactory(Repository repository, String workspaceName) {
        this.repository_ = repository;
        this.workspaceName_ = workspaceName;
    }

    /** repository */
    private Repository repository_;

    public void setRepository(Repository repository) {
        this.repository_ = repository;
    }

    /** workspace name */
    private String workspaceName_;

    public String getWorkspaceName() {
        return workspaceName_;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName_ = workspaceName;
    }

    /** user */
    String user_;

    public String getUser() {
        return user_;
    }

    public void setUser(String user) {
        this.user_ = user;
    }

    /** password */
    String password_;

    public String getPassword() {
        return password_;
    }

    public void setPassword(String password) {
        this.password_ = password;
    }

    /** namespaces */
    private Properties namespaces_;

    public void setNamespaces(Properties namespaces) {
        this.namespaces_ = namespaces;
    }

    public synchronized void addNamespace(String namespace, String url) {
        if (this.namespaces_ == null) {
            this.namespaces_ = new Properties();
        }
        this.namespaces_.put(namespace, url);
    }

    /** observation register. */
    private JcrObservationComponentRegister observationRegister_;

    public void setObservationComponentRegister(
            JcrObservationComponentRegister observationRegister) {
        this.observationRegister_ = observationRegister;
    }

    /** event listener definitions */
    private Set<?> eventListeners = null;

    /**
     * get session.
     * 
     * @return
     */
    public Session getSession() throws JcrRepositoryRuntimeException {
        try {
            if (this.workspaceName_ == null || "".equals(this.workspaceName_)) {
                this.workspaceName_ = DEFAULT_WORKSPACE_NAME;
            }
            if (this.user_ == null || "".equals(this.user_)) {
                this.user_ = DEFAULT_USER;
            }
            if (this.password_ == null || "".equals(this.password_)) {
                this.password_ = DEFAULT_PASSWORD;
            }
            if (this.eventListeners == null) {
                this.observationRegister_.registerAll();
                this.eventListeners = this.observationRegister_.getJcrObservations();
            }
            Session session = this.repository_.login(new SimpleCredentials(this.user_,
                    this.password_.toCharArray()), this.workspaceName_);

            this.registerNamespaces(session, this.namespaces_);
            this.registerNodeType(session);
            this.addEventListeners(session);

            logger_.debug("::: session:[" + session + "] :::");

            return session;

        } catch (LoginException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (NoSuchWorkspaceException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (RepositoryException e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }

    /**
     * get node type files.
     * 
     * @return
     */
    private String[] getNodeTypeFiles() {
        List<String> files = new ArrayList<String>();
        files.add(this.getClass().getResource("ocm-discriminator.xml").getPath());

        return files.toArray(new String[files.size()]);
    }

    /**
     * add event listeners.
     * 
     * @param session
     * @return
     * @throws JcrRepositoryRuntimeException
     */
    protected void addEventListeners(Session session) throws JcrRepositoryRuntimeException {
        if (this.eventListeners == null || this.eventListeners.size() == 0) {
            return;
        }
        try {
            Workspace workspace = session.getWorkspace();
            EventListenerDefinition eventDefinition = null;

            for (Iterator i = this.eventListeners.iterator(); i.hasNext();) {
                Object bean = i.next();
                eventDefinition = new EventListenerDefinition();

                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
                    Method getter = property.getReadMethod();

                    if (getter == null) continue;
                    eventDefinition.setProperty(getter.getName(), getter.invoke(bean, null));
                }
                workspace.getObservationManager().addEventListener(eventDefinition.getListener(),
                        eventDefinition.getEventTypes(), eventDefinition.getAbsPath(),
                        eventDefinition.isDeep(), eventDefinition.getUuids(),
                        eventDefinition.getNodeTypeNames(), eventDefinition.getNoLocal());
            }
            
        } catch (IllegalAccessException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (IntrospectionException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (InvocationTargetException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (RepositoryException e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }

    /**
     * register node type.
     * 
     * @throws JcrRepositoryRuntimeException
     */
    private void registerNodeType(Session session) throws JcrRepositoryRuntimeException {
        String[] nodeTypeFiles = this.getNodeTypeFiles();

        if (nodeTypeFiles == null || nodeTypeFiles.length == 0)
            return;
        try {
            Workspace workspace = session.getWorkspace();
            NodeTypeManager ntMgr = workspace.getNodeTypeManager();
            NodeTypeRegistry ntReg = ((NodeTypeManagerImpl) ntMgr).getNodeTypeRegistry();

            for (String file : nodeTypeFiles) {
                InputStream xml = new FileInputStream(file);
                NodeTypeDef[] types = NodeTypeReader.read(xml);

                for (NodeTypeDef def : types) {
                    try {
                        ntReg.getNodeTypeDef(def.getName());
                    } catch (NoSuchNodeTypeException nsne) {
                        ntReg.registerNodeType(def);
                    }
                }
            }
        } catch (InvalidNodeTypeDefException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (IOException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } catch (RepositoryException e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }

    /**
     * register namespaces.
     * 
     * @param session
     * @param namespaces
     * @throws JcrRepositoryRuntimeException
     */
    private void registerNamespaces(Session session, Properties namespaces)
            throws JcrRepositoryRuntimeException {

        try {
            String[] jcrNamespaces = session.getWorkspace().getNamespaceRegistry().getPrefixes();

            for (Enumeration<?> e = namespaces.propertyNames(); e.hasMoreElements();) {
                String namespace = (String) e.nextElement();
                String url = namespaces.getProperty(namespace);

                boolean isCreateNamespace = true;
                for (String n : jcrNamespaces) {
                    if (namespace.equals(n)) {
                        isCreateNamespace = false;
                        break;
                    }
                }
                if (isCreateNamespace) {
                    session.getWorkspace().getNamespaceRegistry().registerNamespace(namespace, url);
                }
            }

        } catch (RepositoryException e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }

    /**
     * register node type.
     * 
     * @throws JcrRepositoryRuntimeException
     */
    protected void unregisterNodeType() throws JcrRepositoryRuntimeException {
    }

    /**
     * register namespaces.
     * 
     * @throws JcrRepositoryRuntimeException
     */
    protected void unregisterNamespaces() throws JcrRepositoryRuntimeException {
    }

    /**
     * destroy session factory.
     * <p>
     * unregister node types and unregister namespaces.
     * </p>
     * 
     * @throws JcrRepositoryRuntimeException
     */
    public void destroy() throws JcrRepositoryRuntimeException {
        this.unregisterNodeType();
        this.unregisterNamespaces();

        logger_.debug("::: JcrSessionFactory#destroy :::");
    }

}
