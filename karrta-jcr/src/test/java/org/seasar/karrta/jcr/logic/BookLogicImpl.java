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
package org.seasar.karrta.jcr.logic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.ocm.query.Filter;
import org.apache.jackrabbit.ocm.query.Query;
import org.apache.jackrabbit.ocm.query.QueryManager;
import org.seasar.karrta.jcr.node.BookNode;
import org.seasar.karrta.jcr.ocm.BookOcm;

/**
 * book logic.
 * 
 * @author yosukehara
 * 
 */
public class BookLogicImpl implements BookLogic{
    private static final Log logger_ = LogFactory.getLog(BookLogicImpl.class);

    /** query manager */
    private QueryManager queryManager_;

    public void setQueryManager(QueryManager queryManager) {
        this.queryManager_ = queryManager;
        
        logger_.debug("::: queryManager:[" + queryManager + "]");
    }

    /** book ocm */
    private BookOcm bookOcm_;

    public void setBookOcm(BookOcm bookOcm) {
        this.bookOcm_ = bookOcm;
        
        logger_.debug("::: bookOcm:[" + bookOcm + "]");
    }

    /**
     * create.
     * 
     * @param book
     */
    public void create(BookNode book) {
        this.bookOcm_.create(book);
    }

    /**
     * update.
     * 
     * @param book
     */
    public void update(BookNode book) {
        this.bookOcm_.update(book);
    }

    /**
     * remove.
     * 
     * @param book
     */
    public void remove(BookNode book) {
        this.bookOcm_.remove(book);
    }

    /**
     * find by id.
     * 
     * @param id
     * @return
     */
    public BookNode findById(long id) {
        Filter filter =
            this.queryManager_.createFilter(BookNode.class)
                              .addEqualTo("id", String.valueOf(id));
        
        Query query = this.queryManager_.createQuery(filter);
        return this.bookOcm_.findById(query);
    }
    
    /**
     * find by ids.
     * 
     * @param ids
     * @return
     */
    public BookNode[] findByIds(String[] ids) {
        Filter filter =
            queryManager_.createFilter(BookNode.class)
                         .addEqualTo("id", ids[0])
                         .addOrFilter(
                            queryManager_.createFilter(BookNode.class)
                                         .addEqualTo("id", ids[1]));
        
        Query query = this.queryManager_.createQuery(filter);
        return this.bookOcm_.findByIds(query);
    }
    
    /**
     * fulltext-search.
     * 
     * @param keyword
     * @return
     */
    public BookNode[] findByKeyword(String keyword) {
        Filter filter = queryManager_.createFilter(BookNode.class)
                                     .addContains("title", keyword);
        
        Query query = this.queryManager_.createQuery(filter);
        return this.bookOcm_.findByIds(query);
    }

}
