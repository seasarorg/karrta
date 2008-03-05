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
import org.seasar.karrta.jcr.node.BookNode;
import org.seasar.karrta.jcr.ocm.BookOcm;
import org.seasar.karrta.jcr.service.BaseService;
import org.seasar.karrta.jcr.service.BookService;

/**
 * book logic.
 * 
 * @author yosukehara
 * 
 */
public class BookServiceImpl extends BaseService implements BookService{
    private static final Log logger_ = LogFactory.getLog(BookServiceImpl.class);

    /** book ocm */
    private BookOcm bookOcm_;

    public void setBookOcm(BookOcm bookOcm) {
        this.bookOcm_ = bookOcm;
        
        logger_.debug("::: bookOcm:[" + bookOcm + "]");
    }

    /*
     * @see org.seasar.karrta.jcr.service.BookService#create(org.seasar.karrta.jcr.node.BookNode)
     */
    public void create(BookNode book) {
        this.bookOcm_.create(book);
    }

    /*
     * @see org.seasar.karrta.jcr.service.BookService#update(org.seasar.karrta.jcr.node.BookNode)
     */
    public void update(BookNode book) {
        BookNode bookNode = this.findById(book.getId());
        bookNode.setTitle(book.getTitle());
        logger_.debug(bookNode);
        
        this.bookOcm_.update(book);
        
        bookNode = this.findById(bookNode.getId());
        logger_.debug(bookNode);
    }

    /*
     * @see org.seasar.karrta.jcr.service.BookService#remove(org.seasar.karrta.jcr.node.BookNode)
     */
    public void remove(BookNode book) {
        this.bookOcm_.remove(book);
    }

    /*
     * @see org.seasar.karrta.jcr.service.BookService#findById(long)
     */
    public BookNode findById(long id) {
        Filter filter =
            this.queryManager_.createFilter(BookNode.class)
                              .addEqualTo("id", String.valueOf(id));
        
        Query query = this.queryManager_.createQuery(filter);
        return this.bookOcm_.findById(query);
    }
    
    /*
     * @see org.seasar.karrta.jcr.service.BookService#findByIds(java.lang.String[])
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
    
    /*
     * (non-Javadoc)
     * @see org.seasar.karrta.jcr.service.BookService#findByKeyword(java.lang.String)
     */
    public BookNode[] findByKeyword(String keyword) {
        Filter filter = queryManager_.createFilter(BookNode.class)
                                     .addContains("title", keyword);
        
        Query query = this.queryManager_.createQuery(filter);
        return this.bookOcm_.findByIds(query);
    }

    /*
     * @see org.seasar.karrta.jcr.service.BookService#afterNodeAdded(java.lang.String)
     */
    public void afterNodeAdded(String path) {
    	logger_.debug("::: [" + path + "] :::");
    }
    
    /*
     * @see org.seasar.karrta.jcr.service.BookService#afterNodeRemoved(java.lang.String)
     */
    public void afterNodeRemoved(String path) {
    	logger_.debug("::: [" + path + "] :::");
    }
}
