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
package org.seasar.karrta.jcr.service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;

import org.apache.jackrabbit.ocm.query.QueryManager;

/**
 * base service.
 * 
 * @author yosukehara
 * 
 */
public abstract class BaseService {
    /** ocm query manager */
    protected QueryManager ocmQueryManager_;

    public void setOcmQueryManager(QueryManager ocmQueryManager) {
        this.ocmQueryManager_ = ocmQueryManager;
    }

    /** query manager */
    protected javax.jcr.query.QueryManager queryManager_;

    public void setQueryManager(javax.jcr.query.QueryManager queryManager) {
        this.queryManager_ = queryManager;
    }

    /**
     * get UUID by node path.
     * 
     * @param queryResultNodeIterator
     * @param path
     * @return
     * @throws RepositoryException
     */
    protected String getUUIDByNodePath(NodeIterator queryResultNodeIterator, String path)
        throws RepositoryException {

        String uuid = null;

        while (queryResultNodeIterator.hasNext()) {
            Node node = queryResultNodeIterator.nextNode();
            if (node == null || !node.getPath().equals(path)) continue;

            PropertyIterator properties = node.getProperties("jcr:uuid");
            Property property = properties.nextProperty();
            uuid = property.getString();
        }
        return uuid;
    }
}
