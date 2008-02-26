package org.seasar.karrta.jcr.ocm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.karrta.jcr.node.BookNode;

public class TestBookNode extends S2TestCase {
    /** logger */
    private static final Log logger_ = LogFactory.getLog(TestBookNode.class);
    
    private BookOcm bookOcm;
    
    public void setUp() {
        this.include("ocm_test.dicon");
    }
    
    /**
     * 
     */
    public void test1() {
        logger_.debug("::: test1 :::");
        
        BookNode bookNode = new BookNode();
        bookNode.setTitle("");
        
        this.bookOcm.create(new BookNode());
        this.bookOcm.update(new BookNode());
        this.bookOcm.remove(new BookNode());
    }
}
