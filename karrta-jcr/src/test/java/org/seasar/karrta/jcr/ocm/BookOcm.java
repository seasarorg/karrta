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

import org.apache.jackrabbit.ocm.query.Query;
import org.seasar.karrta.jcr.annotation.Ocm;
import org.seasar.karrta.jcr.node.BookNode;

/**
 * 
 * @author yosukehara
 * 
 */
@Ocm(bean = BookNode.class)
public interface BookOcm {

    /**
     * create.
     * 
     * @param book
     */
    void create(BookNode book);

    /**
     * update.
     * 
     * @param book
     */
    void update(BookNode book);

    /**
     * remove.
     * 
     * @param book
     */
    void remove(BookNode book);

    /**
     * get object by uuid.
     * 
     * @param uuid
     * @return
     */
    BookNode getObjectByUuid(String uuid);
    
    /**
     * select.
     * 
     * @param query
     * @return
     */
    BookNode selectNode(Query query);

    /**
     * find by ids.
     * 
     * @param query
     * @return
     */
    BookNode[] selectNodes(Query query);
}
