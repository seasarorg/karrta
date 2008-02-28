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
package org.seasar.karrta.jcr.ocm;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.karrta.jcr.logic.BookLogicImpl;
import org.seasar.karrta.jcr.node.BookNode;

/**
 * test book ocm.
 * 
 * <p>insert/update/remove/query</p>
 * 
 * @author yosukehara
 *
 */
public class TestBookOcm extends S2TestCase {
    /** logger */
    private Log logger_ = LogFactory.getLog(TestBookOcm.class);
    
    private BookLogicImpl bookLogic_;
    
    /*
     * @see junit.framework.TestCase#setUp()
     */
    public void setUp() {
        this.include("ocm_test.dicon");
    }
    
    /**
     * test#1
     */
    public void test1() {
        logger_.debug("### create.start ###");
        // #1
        BookNode bookNode1 = this.getBookNode1();
        this.bookLogic_.create(bookNode1);

        BookNode bookNodeResult = this.bookLogic_.findById(4873112710L);
        assertEquals(bookNode1.getPath(), bookNodeResult.getPath());
        assertEquals(bookNode1.getId(), bookNodeResult.getId());
        assertEquals(bookNode1.getTitle(), bookNodeResult.getTitle());
        assertEquals(bookNode1.getIsbn13(), bookNodeResult.getIsbn13());
        assertEquals(bookNode1.getPubDate(), bookNodeResult.getPubDate());
        assertEquals(bookNode1.getDescription(), bookNodeResult.getDescription());

        logger_.debug(bookNodeResult.toString());
        logger_.debug("### create.end ###\n");
        
        
        //#2
        logger_.debug("### create.start ###");
        this.bookLogic_.create(this.getBookNode2());
        logger_.debug("### create.end ###\n");
        
        
        // update.
        logger_.debug("### update1.start ###");
        this.bookLogic_.update(this.getBookNode1ForUpdate());
        logger_.debug("### update1.end ###\n");
        
        logger_.debug("### update2.start ###");
        this.bookLogic_.update(this.getBookNode2ForUpdate());
        logger_.debug("### update2.end ###\n");
        
        
        // find by ids.
        logger_.debug("### find by ids.start ###");
        BookNode[] bookNodeResults =
            this.bookLogic_.findByIds(new String[]{"4873112710", "4873113539"});
        this.dump(bookNodeResults);
        assertEquals(2, bookNodeResults.length);
        logger_.debug("### find by ids.end ###\n");
    }
    
    public void test2() {
        // retrieve.
        logger_.debug("### find by id.start ###");
        BookNode bookNode1 = this.bookLogic_.findById(4873112710L);
        logger_.debug(bookNode1);
        logger_.debug("### find by id.end ###\n");
        
        logger_.debug("### find by id.start ###");
        BookNode bookNode2 = this.bookLogic_.findById(4873113539L);
        logger_.debug(bookNode2);
        logger_.debug("---");
        logger_.debug("### find by id.end ###\n");
        
        logger_.debug("### fulltext-search.start ###");
        BookNode[] bookNodeResults = this.bookLogic_.findByKeyword("update");
        this.dump(bookNodeResults);
        logger_.debug("### fulltext-search.end ###\n");
        
        
        // remove.
        logger_.debug("### remove.start ###");
        this.bookLogic_.remove(this.bookLogic_.findById(4873112710L));
        logger_.debug("### remove.end ###\n");
        
        logger_.debug("### remove.start ###");
        this.bookLogic_.remove(this.bookLogic_.findById(4873113539L));
        logger_.debug("### remove.end ###\n");
        
        
        // retrieve.
        logger_.debug("### find by id.start ###");
        bookNode1 = this.bookLogic_.findById(4873112710L);
        assertNull(bookNode1);
        logger_.debug("### find by id.end ###\n");
        
        logger_.debug("### find by ids.start ###");
        bookNodeResults = this.bookLogic_.findByIds(new String[]{"4873112710", "4873113539"});
        assertEquals(0, bookNodeResults.length);
        logger_.debug("### find by ids.end ###\n");
    }
    
    private void dump(BookNode[] bookNodeResults) {
        if (bookNodeResults == null) return;
        logger_.debug("::: [" + bookNodeResults.length + "] :::");
        
        for (BookNode book : bookNodeResults) {
            logger_.debug(book.toString());
        }
    }
    
    private BookNode getBookNode1() {
        BookNode bookNode1 = new BookNode();
        bookNode1.setPath("/books");
        bookNode1.setId(4873112710L);
        bookNode1.setTitle("Mind Hacks—実験で知る脳と心のシステム");
        bookNode1.setIsbn13("978-4873112718");
        
        Calendar pubDate = new GregorianCalendar(2005,11,1);
        bookNode1.setPubDate(pubDate.getTime());
        
        StringBuilder desc = new StringBuilder();
        desc.append("最新の脳科学を基にした解説と簡単な実験で、脳と心のはたらきを説明する新しい脳の本。");
        bookNode1.setDescription(desc.toString());
        
        return bookNode1;
    }
    
    private BookNode getBookNode1ForUpdate() {
        BookNode bookNode1 = this.bookLogic_.findById(4873112710L);
        bookNode1.setTitle("Mind Hacks - 実験で知る脳と心のシステムを理解する本 - update");
        return bookNode1;
    }
    
    private BookNode getBookNode2() {
        BookNode bookNode2 = new BookNode();
        bookNode2.setPath("/books");
        bookNode2.setId(4873113539L);
        bookNode2.setTitle("RESTful Webサービス");
        bookNode2.setIsbn13("978-4873113531");
        
        Calendar pubDate = new GregorianCalendar(2005,11,21);
        bookNode2.setPubDate(pubDate.getTime());
        
        StringBuilder desc = new StringBuilder();
        desc.append("RESTful Webサービスを深く理解できる本。");
        bookNode2.setDescription(desc.toString());
        
        return bookNode2;
    }
    
    private BookNode getBookNode2ForUpdate() {
        BookNode bookNode2 = this.bookLogic_.findById(4873113539L);
        bookNode2.setTitle("RESTful Webサービスを深く理解できる本 - update");
        return bookNode2;
    }
}
