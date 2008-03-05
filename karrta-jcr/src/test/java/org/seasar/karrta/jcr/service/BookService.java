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

import org.seasar.karrta.jcr.node.BookNode;

/**
 * book logic
 * 
 * @author yosukehara
 * 
 */
public interface BookService {
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
	 * find by id.
	 * 
	 * @param id
	 * @return
	 */
	BookNode findById(long id);

	/**
	 * find by ids.
	 * 
	 * @param ids
	 * @return
	 */
	BookNode[] findByIds(String[] ids);

	/**
	 * find by keyword.
	 * 
	 * @param keyword
	 * @return
	 */
	BookNode[] findByKeyword(String keyword);
}
