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
    protected javax.jcr.query.QueryManager  queryManager_;
    
    public void setQueryManager(javax.jcr.query.QueryManager queryManager) {
        this.queryManager_ = queryManager;
    }
}
