package org.seasar.karrta.jcr.logic;

import org.seasar.karrta.jcr.node.BookNode;

public interface BookLogic {
    void create(BookNode book);
    void update(BookNode book);
    void remove(BookNode book);
    BookNode findById(long id);
    BookNode[] findByIds(String[] ids);
    BookNode[] findByKeyword(String keyword);
}
