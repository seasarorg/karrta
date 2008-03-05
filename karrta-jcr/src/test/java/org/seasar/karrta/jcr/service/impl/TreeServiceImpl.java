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
package org.seasar.karrta.jcr.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.ocm.query.Filter;
import org.apache.jackrabbit.ocm.query.Query;
import org.seasar.karrta.jcr.node.ParentTreeNode;
import org.seasar.karrta.jcr.ocm.TreeOcm;
import org.seasar.karrta.jcr.service.BaseService;
import org.seasar.karrta.jcr.service.TreeService;

/**
 * tree service imple.
 * 
 * @author yosukehara
 *
 */
public class TreeServiceImpl extends BaseService implements TreeService {
    private static final Log logger_ = LogFactory.getLog(TreeServiceImpl.class);
    
    /** tree ocm */
    private TreeOcm treeOcm_;
    
    public void setTreeOcm(TreeOcm treeOcm) {
        this.treeOcm_ = treeOcm;
    }
    
    /*
     * @see org.seasar.karrta.jcr.service.TreeService#create(org.seasar.karrta.jcr.node.ParentNode)
     */
    public void create(ParentTreeNode parent) {
        logger_.debug("::: TreeServiceImpl#create");
        this.treeOcm_.create(parent);
    }

    /*
     * @see org.seasar.karrta.jcr.service.TreeService#findById(long)
     */
    public ParentTreeNode findById(long id) {
        logger_.debug("::: TreeServiceImpl#findById:[" + this.ocmQueryManager_ + "]");
        Filter filter =
            this.ocmQueryManager_.createFilter(ParentTreeNode.class)
                              .addEqualTo("id", String.valueOf(id));
        
        Query query = this.ocmQueryManager_.createQuery(filter);
        return this.treeOcm_.findById(query);
    }

    /*
     * @see org.seasar.karrta.jcr.service.TreeService#remove(org.seasar.karrta.jcr.node.ParentNode)
     */
    public void remove(ParentTreeNode parent) {
        logger_.debug("::: TreeServiceImpl#remove");
        this.treeOcm_.remove(parent);
    }

    /*
     * @see org.seasar.karrta.jcr.service.TreeService#update(org.seasar.karrta.jcr.node.ParentNode)
     */
    public void update(ParentTreeNode parent) {
        logger_.debug("::: TreeServiceImpl#update");
        this.treeOcm_.update(parent);
    }

}
