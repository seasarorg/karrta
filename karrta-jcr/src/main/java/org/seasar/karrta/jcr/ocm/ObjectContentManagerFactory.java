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

import java.util.List;

import javax.jcr.Session;

import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;
import org.apache.jackrabbit.ocm.mapper.Mapper;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl;
import org.apache.jackrabbit.ocm.query.QueryManager;
import org.seasar.karrta.jcr.exception.JcrNotMappingClassException;
import org.seasar.karrta.jcr.exception.JcrNotSessionException;

/**
 * object content manager factory.
 * 
 * @author yosukehara
 * 
 */
public class ObjectContentManagerFactory {
    
	public ObjectContentManagerFactory() {
	}

	/**
	 * @param session
	 */
	public ObjectContentManagerFactory(Session session) {

	}

	/** session */
	private Session session_;

	public Session getSession() {
	    return session_;
	}
	
	public synchronized void setSession(Session session) {
		this.session_ = session;
	}

	/** mapping class list */
	List<Class> mappingClasses_;

	public synchronized void setMappingClasses(List<Class> mappingClasses) {
		this.mappingClasses_ = mappingClasses;
	}

	/** query manager */
	QueryManager queryManager_;

	public synchronized QueryManager getQueryManager() {
		return this.getObjectContentManager().getQueryManager();
	}

	/**
	 * get object content manager(ocm).
	 * 
	 * @return
	 */
	public synchronized ObjectContentManager getObjectContentManager()
			throws JcrNotSessionException, JcrNotMappingClassException {

		if (this.session_ == null) {
			throw new JcrNotSessionException("");
		}
		if (this.mappingClasses_ == null || this.mappingClasses_.size() == 0) {
			throw new JcrNotMappingClassException("");
		}
		
		Mapper mapper = new AnnotationMapperImpl(this.mappingClasses_);
		ObjectContentManager ocm = new ObjectContentManagerImpl(this.session_, mapper);

		return ocm;
	}

}
