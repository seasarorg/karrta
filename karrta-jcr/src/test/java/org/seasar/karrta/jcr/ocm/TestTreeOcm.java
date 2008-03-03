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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.karrta.jcr.node.ChildTreeNode;
import org.seasar.karrta.jcr.node.ParentTreeNode;
import org.seasar.karrta.jcr.service.TreeService;

/**
 * test tree ocm.
 * 
 * @author yosukehara
 *
 */
public class TestTreeOcm extends S2TestCase {
    private static final Log logger_ = LogFactory.getLog(TestTreeOcm.class);
    
    TreeService treeService_;
    
    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    public void setUp() {
        this.include("ocm_test.dicon");
    }

    /**
     * test create#1 - simple.
     */
    public void test1() {
        Date now = new Date();
        ParentTreeNode parent = new ParentTreeNode();
        parent.setPath("/karrta");
        parent.setId(1L);
        parent.setTitle("Karrta Mind Map");
        parent.setCreatedAt(now);
        parent.setModified(now);
        
        List<ChildTreeNode> childTree1 = new ArrayList<ChildTreeNode>();
        childTree1.add(new ChildTreeNode(1L,"Child 1-1"));
        childTree1.add(new ChildTreeNode(1L,"Child 1-2"));
        
        parent.addChild(new ChildTreeNode(1L,"Child 1", childTree1));
        parent.addChild(new ChildTreeNode(2L,"Child 2"));
        parent.addChild(new ChildTreeNode(3L,"Child 3"));
        
        this.treeService_.create(parent);
        
        parent = this.treeService_.findById(1);
        assertNotNull(parent);
        assertEquals(1L, parent.getId());
        assertEquals("Karrta Mind Map", parent.getTitle());
    }
    
    public void test2() {
        ParentTreeNode parent = this.treeService_.findById(1);
        logger_.debug("Parent-Node:[\n");
        logger_.debug(parent);
        logger_.debug("---");
        
        assertNotNull(parent);
    }
    
    public void test3() {
        ParentTreeNode parent = this.treeService_.findById(1);
        parent.setTitle("Karrta Mind Map - Update");
        parent.setModified(new Date());
        
        this.treeService_.update(parent);
        
        parent = this.treeService_.findById(1);
        assertNotNull(parent);
        assertEquals(1L, parent.getId());
        assertEquals("Karrta Mind Map - Update", parent.getTitle());
    }
    
    public void test4() {
        ParentTreeNode parent = this.treeService_.findById(1);
        this.treeService_.remove(parent);
        
        parent = this.treeService_.findById(1);
        assertNull(parent);
    }
}
