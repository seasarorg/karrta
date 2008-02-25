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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.jcr.Credentials;
import javax.jcr.LoginException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Workspace;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeTypeManager;

import org.apache.jackrabbit.core.nodetype.InvalidNodeTypeDefException;
import org.apache.jackrabbit.core.nodetype.NodeTypeDef;
import org.apache.jackrabbit.core.nodetype.NodeTypeManagerImpl;
import org.apache.jackrabbit.core.nodetype.NodeTypeRegistry;
import org.apache.jackrabbit.core.nodetype.xml.NodeTypeReader;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * 
 * @author yosuke
 * 
 */
public class JcrSessionFactory {
    /** default workspace name */
    private static final String DEFAULT_WORKSPACE_NAME = "default";

    public JcrSessionFactory() {}

    /**
     * @param repository
     * @param workspaceName
     * @param credentials
     */
    public JcrSessionFactory(Repository repository, String workspaceName, Credentials credentials) {
        this.repository_ = repository;
        this.workspaceName_ = workspaceName;
        this.credentials_ = credentials;
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

    /** credentials */
    private Credentials credentials_;

    public Credentials getCredentials() {
        return credentials_;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials_ = credentials;
    }

    /** node type files * */
    private String[] nodeTypeFiles_;

    public void setNodeTypeFiles(String[] nodeTypeFiles) {
        this.nodeTypeFiles_ = nodeTypeFiles;
    }

    private Properties namespaces_;

    public void setNamespaces(Properties namespaces) {
        this.namespaces_ = namespaces;
    }

    /**
     * 
     * @return
     */
    public Session getSession() throws JcrRepositoryRuntimeException {
        try {
            if (this.workspaceName_ == null || "".equals(this.workspaceName_)) {
                this.workspaceName_ = DEFAULT_WORKSPACE_NAME;
            }
            if (this.credentials_ == null) {
                this.credentials_ = new SimpleCredentials("username", "password".toCharArray());
            }
            Session session = this.repository_.login(this.credentials_, this.workspaceName_);
            this.registerNodeType(session, this.nodeTypeFiles_);
            this.registerNamespaces(session, this.namespaces_);
            this.addEventListeners(session);

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
     * add event listeners.
     * 
     * @param session
     * @return
     * @throws JcrRepositoryRuntimeException
     */
    protected void addEventListeners(Session session) throws JcrRepositoryRuntimeException {
    // add event listener.
    }

    /**
     * register node type.
     * 
     * @throws JcrRepositoryRuntimeException
     */
    private void registerNodeType(Session session, String[] nodeTypeFiles)
        throws JcrRepositoryRuntimeException {
        if (nodeTypeFiles == null || nodeTypeFiles.length == 0) return;
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
                String namespace = (String)e.nextElement();
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
    }

}
