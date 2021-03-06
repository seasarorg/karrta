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
package org.seasar.karrta.jcr.commons;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

public class JcrConstants {
	/** jcr:autoCreated */
	public static final String JCR_AUTOCREATED = "autoCreated";
	/** jcr:baseVersion */
	public static final String JCR_BASEVERSION = "baseVersion";
	/** jcr:child */
	public static final String JCR_CHILD = "child";
	/** jcr:childNodeDefinition */
	public static final String JCR_CHILDNODEDEFINITION = "childNodeDefinition";
	/** jcr:content */
	public static final String JCR_CONTENT = "content";
	/** jcr:created */
	public static final String JCR_CREATED = "created";
	/** jcr:data */
	public static final String JCR_DATA = "data";
	/** jcr:defaultPrimaryType */
	public static final String JCR_DEFAULTPRIMARYTYPE = "defaultPrimaryType";
	/** jcr:defaultValues */
	public static final String JCR_DEFAULTVALUES = "defaultValues";
	/** jcr:encoding */
	public static final String JCR_ENCODING = "encoding";
	/** jcr:frozenMixinTypes */
	public static final String JCR_FROZENMIXINTYPES = "frozenMixinTypes";
	/** jcr:frozenNode */
	public static final String JCR_FROZENNODE = "frozenNode";
	/** jcr:frozenPrimaryType */
	public static final String JCR_FROZENPRIMARYTYPE = "frozenPrimaryType";
	/** jcr:frozenUuid */
	public static final String JCR_FROZENUUID = "frozenUuid";
	/** jcr:hasOrderableChildNodes */
	public static final String JCR_HASORDERABLECHILDNODES = "hasOrderableChildNodes";
	/** jcr:isCheckedOut */
	public static final String JCR_ISCHECKEDOUT = "isCheckedOut";
	/** jcr:isMixin */
	public static final String JCR_ISMIXIN = "isMixin";
	/** jcr:language */
	public static final String JCR_LANGUAGE = "language";
	/** jcr:lastModified */
	public static final String JCR_LASTMODIFIED = "lastModified";
	/** jcr:lockIsDeep */
	public static final String JCR_LOCKISDEEP = "lockIsDeep";
	/** jcr:lockOwner */
	public static final String JCR_LOCKOWNER = "lockOwner";
	/** jcr:mandatory */
	public static final String JCR_MANDATORY = "mandatory";
	/** jcr:mergeFailed */
	public static final String JCR_MERGEFAILED = "mergeFailed";
	/** jcr:mimeType */
	public static final String JCR_MIMETYPE = "mimeType";
	/** jcr:mixinTypes */
	public static final String JCR_MIXINTYPES = "mixinTypes";
	/** jcr:multiple */
	public static final String JCR_MULTIPLE = "multiple";
	/** jcr:name */
	public static final String JCR_NAME = "name";
	/** jcr:nodeTypeName */
	public static final String JCR_NODETYPENAME = "nodeTypeName";
	/** jcr:onParentVersion */
	public static final String JCR_ONPARENTVERSION = "onParentVersion";
	/** jcr:predecessors */
	public static final String JCR_PREDECESSORS = "predecessors";
	/** jcr:primaryItemName */
	public static final String JCR_PRIMARYITEMNAME = "primaryItemName";
	/** jcr:primaryType */
	public static final String JCR_PRIMARYTYPE = "primaryType";
	/** jcr:propertyDefinition */
	public static final String JCR_PROPERTYDEFINITION = "propertyDefinition";
	/** jcr:protected */
	public static final String JCR_PROTECTED = "protected";
	/** jcr:requiredPrimaryTypes */
	public static final String JCR_REQUIREDPRIMARYTYPES = "requiredPrimaryTypes";
	/** jcr:requiredType */
	public static final String JCR_REQUIREDTYPE = "requiredType";
	/** jcr:rootVersion */
	public static final String JCR_ROOTVERSION = "rootVersion";
	/** jcr:sameNameSiblings */
	public static final String JCR_SAMENAMESIBLINGS = "sameNameSiblings";
	/** jcr:statement */
	public static final String JCR_STATEMENT = "statement";
	/** jcr:successors */
	public static final String JCR_SUCCESSORS = "successors";
	/** jcr:supertypes */
	public static final String JCR_SUPERTYPES = "supertypes";
	/** jcr:system */
	public static final String JCR_SYSTEM = "system";
	/** jcr:uuid */
	public static final String JCR_UUID = "uuid";
	/** jcr:valueConstraints */
	public static final String JCR_VALUECONSTRAINTS = "valueConstraints";
	/** jcr:versionHistory */
	public static final String JCR_VERSIONHISTORY = "versionHistory";
	/** jcr:versionLabels */
	public static final String JCR_VERSIONLABELS = "versionLabels";
	/** jcr:versionStorage */
	public static final String JCR_VERSIONSTORAGE = "versionStorage";
	/** jcr:versionableUuid */
	public static final String JCR_VERSIONABLEUUID = "versionableUuid";
	/** Pseudo property jcr:path used with query results */
	public static final String JCR_PATH = "path";
	/** Pseudo property jcr:score used with query results */
	public static final String JCR_SCORE = "score";
	/** mix:lockable */
	public static final String MIX_LOCKABLE = "lockable";
	/** mix:referenceable */
	public static final String MIX_REFERENCEABLE = "referenceable";
	/** mix:versionable */
	public static final String MIX_VERSIONABLE = "versionable";
	/** nt:base */
	public static final String NT_BASE = "base";
	/** nt:childNodeDefinition */
	public static final String NT_CHILDNODEDEFINITION = "childNodeDefinition";
	/** nt:file */
	public static final String NT_FILE = "file";
	/** nt:folder */
	public static final String NT_FOLDER = "folder";
	/** nt:frozenNode */
	public static final String NT_FROZENNODE = "frozenNode";
	/** nt:hierarchyNode */
	public static final String NT_HIERARCHYNODE = "hierarchyNode";
	/** nt:linkedFile */
	public static final String NT_LINKEDFILE = "linkedFile";
	/** nt:nodeType */
	public static final String NT_NODETYPE = "nodeType";
	/** nt:propertyDefinition */
	public static final String NT_PROPERTYDEFINITION = "propertyDefinition";
	/** nt:query */
	public static final String NT_QUERY = "query";
	/** nt:resource */
	public static final String NT_RESOURCE = "resource";
	/** nt:unstructured */
	public static final String NT_UNSTRUCTURED = "unstructured";
	/** nt:version */
	public static final String NT_VERSION = "version";
	/** nt:versionHistory */
	public static final String NT_VERSIONHISTORY = "versionHistory";
	/** nt:versionLabels */
	public static final String NT_VERSIONLABELS = "versionLabels";
	/** nt:versionedChild */
	public static final String NT_VERSIONEDCHILD = "versionedChild";

	private boolean cache_ = true;
	private Session session_ = null;

	// JCR related name-spaces
	protected static final String JCR_NS = "http://www.jcp.org/jcr/1.0";
	protected static final String NT_NS  = "http://www.jcp.org/jcr/nt/1.0";
	protected static final String MIX_NS = "http://www.jcp.org/jcr/mix/1.0";

	/** Cache for jcr items. */
	protected final Map<Integer, String> jcrCacheMap = new HashMap<Integer, String>();
	
	/** Cache for nt and mix items. (to avoid String classes and to balance the maps). */
	protected final Map<Integer, String> ntCacheMap = new HashMap<Integer, String>();

	/**
	 * Constructor.
	 * 
	 * @param cache true to cache resolved names, false otherwise.
	 */
	public JcrConstants(Session session, boolean cache) {
		this.cache_ = cache;
		this.session_ = session;
	}

	public JcrConstants(Session session) {
		this(session, true);
	}

	/**
	 * Resolve name.
	 * 
	 * @param namespace
	 * @param property
	 * @return
	 */
	protected String resolveName(String namespace, String property) {
		if (this.cache_) {
			Map<Integer, String> map =
				JCR_NS.hashCode() == namespace.hashCode() ? jcrCacheMap : ntCacheMap;
			
			String result = (String) map.get(new Integer(property.hashCode()));
			if (result == null) {
				result = computeName(namespace, property);
			}
			map.put(new Integer(property.hashCode()), result);
			return result;
		}
		return computeName(namespace, property);
	}

	/**
	 * Computes the actual name.
	 * 
	 * @param namespace
	 * @param property
	 * @return
	 */
	protected String computeName(String namespace, String property) {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(session_.getNamespacePrefix(namespace));
			buffer.append(':');
			buffer.append(property);
			
			return buffer.toString();
		}
		catch (RepositoryException e) {
			throw new RuntimeException("");  // TASK;
		}
	}

	/**
	 * Creates the actual cache.
	 *
	 */
	protected void createCache() {
		jcrCacheMap.put(new Integer(JCR_AUTOCREATED.hashCode()),            computeName(JCR_NS, JCR_AUTOCREATED));
		jcrCacheMap.put(new Integer(JCR_BASEVERSION.hashCode()),            computeName(JCR_NS, JCR_BASEVERSION));
		jcrCacheMap.put(new Integer(JCR_CHILD.hashCode()),                  computeName(JCR_NS, JCR_CHILD));
		jcrCacheMap.put(new Integer(JCR_CHILDNODEDEFINITION.hashCode()),    computeName(JCR_NS,JCR_CHILDNODEDEFINITION));
		jcrCacheMap.put(new Integer(JCR_CONTENT.hashCode()),                computeName(JCR_NS, JCR_CONTENT));
		jcrCacheMap.put(new Integer(JCR_CREATED.hashCode()),                computeName(JCR_NS, JCR_CREATED));
		jcrCacheMap.put(new Integer(JCR_DATA.hashCode()),                   computeName(JCR_NS, JCR_DATA));
		jcrCacheMap.put(new Integer(JCR_DEFAULTPRIMARYTYPE.hashCode()),     computeName(JCR_NS, JCR_DEFAULTPRIMARYTYPE));
		jcrCacheMap.put(new Integer(JCR_DEFAULTVALUES.hashCode()),          computeName(JCR_NS, JCR_DEFAULTVALUES));
		jcrCacheMap.put(new Integer(JCR_ENCODING.hashCode()),               computeName(JCR_NS, JCR_ENCODING));
		jcrCacheMap.put(new Integer(JCR_FROZENMIXINTYPES.hashCode()),       computeName(JCR_NS, JCR_FROZENMIXINTYPES));
		jcrCacheMap.put(new Integer(JCR_FROZENNODE.hashCode()),             computeName(JCR_NS, JCR_FROZENNODE));
		jcrCacheMap.put(new Integer(JCR_FROZENPRIMARYTYPE.hashCode()),      computeName(JCR_NS, JCR_FROZENPRIMARYTYPE));
		jcrCacheMap.put(new Integer(JCR_FROZENUUID.hashCode()),             computeName(JCR_NS, JCR_FROZENUUID));
		jcrCacheMap.put(new Integer(JCR_HASORDERABLECHILDNODES.hashCode()), computeName(JCR_NS, JCR_HASORDERABLECHILDNODES));
		jcrCacheMap.put(new Integer(JCR_ISCHECKEDOUT.hashCode()),           computeName(JCR_NS, JCR_ISCHECKEDOUT));
		jcrCacheMap.put(new Integer(JCR_ISMIXIN.hashCode()),                computeName(JCR_NS, JCR_ISMIXIN));
		jcrCacheMap.put(new Integer(JCR_LANGUAGE.hashCode()),               computeName(JCR_NS, JCR_LANGUAGE));
		jcrCacheMap.put(new Integer(JCR_LASTMODIFIED.hashCode()),           computeName(JCR_NS, JCR_LASTMODIFIED));
		jcrCacheMap.put(new Integer(JCR_LOCKISDEEP.hashCode()),             computeName(JCR_NS, JCR_LOCKISDEEP));
		jcrCacheMap.put(new Integer(JCR_LOCKOWNER.hashCode()),              computeName(JCR_NS, JCR_LOCKOWNER));
		jcrCacheMap.put(new Integer(JCR_MANDATORY.hashCode()),              computeName(JCR_NS, JCR_MANDATORY));
		jcrCacheMap.put(new Integer(JCR_MERGEFAILED.hashCode()),            computeName(JCR_NS, JCR_MERGEFAILED));
		jcrCacheMap.put(new Integer(JCR_MIMETYPE.hashCode()),               computeName(JCR_NS, JCR_MIMETYPE));
		jcrCacheMap.put(new Integer(JCR_MIXINTYPES.hashCode()),             computeName(JCR_NS, JCR_MIXINTYPES));
		jcrCacheMap.put(new Integer(JCR_MULTIPLE.hashCode()),               computeName(JCR_NS, JCR_MULTIPLE));
		jcrCacheMap.put(new Integer(JCR_NAME.hashCode()),                   computeName(JCR_NS, JCR_NAME));
		jcrCacheMap.put(new Integer(JCR_NODETYPENAME.hashCode()),           computeName(JCR_NS, JCR_NODETYPENAME));
		jcrCacheMap.put(new Integer(JCR_ONPARENTVERSION.hashCode()),        computeName(JCR_NS, JCR_ONPARENTVERSION));
		jcrCacheMap.put(new Integer(JCR_PATH.hashCode()),                   computeName(JCR_NS, JCR_PATH));
		jcrCacheMap.put(new Integer(JCR_PREDECESSORS.hashCode()),           computeName(JCR_NS, JCR_PREDECESSORS));
		jcrCacheMap.put(new Integer(JCR_PRIMARYITEMNAME.hashCode()),        computeName(JCR_NS, JCR_PRIMARYITEMNAME));
		jcrCacheMap.put(new Integer(JCR_PRIMARYTYPE.hashCode()),            computeName(JCR_NS, JCR_PRIMARYTYPE));
		jcrCacheMap.put(new Integer(JCR_PRIMARYITEMNAME.hashCode()),        computeName(JCR_NS, JCR_PRIMARYITEMNAME));
		jcrCacheMap.put(new Integer(JCR_PROPERTYDEFINITION.hashCode()),     computeName(JCR_NS, JCR_PROPERTYDEFINITION));
		jcrCacheMap.put(new Integer(JCR_PROTECTED.hashCode()),              computeName(JCR_NS, JCR_PROTECTED));
		jcrCacheMap.put(new Integer(JCR_REQUIREDPRIMARYTYPES.hashCode()),   computeName(JCR_NS, JCR_REQUIREDPRIMARYTYPES));
		jcrCacheMap.put(new Integer(JCR_REQUIREDTYPE.hashCode()),           computeName(JCR_NS, JCR_REQUIREDTYPE));
		jcrCacheMap.put(new Integer(JCR_ROOTVERSION.hashCode()),            computeName(JCR_NS, JCR_ROOTVERSION));
		jcrCacheMap.put(new Integer(JCR_SAMENAMESIBLINGS.hashCode()),       computeName(JCR_NS, JCR_SAMENAMESIBLINGS));
		jcrCacheMap.put(new Integer(JCR_SCORE.hashCode()),                  computeName(JCR_NS, JCR_SCORE));
		jcrCacheMap.put(new Integer(JCR_STATEMENT.hashCode()),              computeName(JCR_NS, JCR_STATEMENT));
		jcrCacheMap.put(new Integer(JCR_SUCCESSORS.hashCode()),             computeName(JCR_NS, JCR_SUCCESSORS));
		jcrCacheMap.put(new Integer(JCR_SUPERTYPES.hashCode()),             computeName(JCR_NS, JCR_SUPERTYPES));
		jcrCacheMap.put(new Integer(JCR_SYSTEM.hashCode()),                 computeName(JCR_NS, JCR_SYSTEM));
		jcrCacheMap.put(new Integer(JCR_UUID.hashCode()),                   computeName(JCR_NS, JCR_UUID));
		jcrCacheMap.put(new Integer(JCR_VALUECONSTRAINTS.hashCode()),       computeName(JCR_NS, JCR_VALUECONSTRAINTS));
		jcrCacheMap.put(new Integer(JCR_VERSIONABLEUUID.hashCode()),        computeName(JCR_NS, JCR_VERSIONABLEUUID));
		jcrCacheMap.put(new Integer(JCR_VERSIONHISTORY.hashCode()),         computeName(JCR_NS, JCR_VERSIONHISTORY));
		jcrCacheMap.put(new Integer(JCR_VERSIONLABELS.hashCode()),          computeName(JCR_NS, JCR_VERSIONLABELS));
		jcrCacheMap.put(new Integer(JCR_VERSIONSTORAGE.hashCode()),         computeName(JCR_NS, JCR_VERSIONSTORAGE));

		jcrCacheMap.put(new Integer(MIX_LOCKABLE.hashCode()),               computeName(MIX_NS, MIX_LOCKABLE));
		jcrCacheMap.put(new Integer(MIX_REFERENCEABLE.hashCode()),          computeName(MIX_NS, MIX_REFERENCEABLE));
		jcrCacheMap.put(new Integer(MIX_VERSIONABLE.hashCode()),            computeName(MIX_NS, MIX_VERSIONABLE));

		jcrCacheMap.put(new Integer(NT_BASE.hashCode()),                    computeName(NT_NS, NT_BASE));
		jcrCacheMap.put(new Integer(NT_CHILDNODEDEFINITION.hashCode()),     computeName(NT_NS, NT_CHILDNODEDEFINITION));
		jcrCacheMap.put(new Integer(NT_FILE.hashCode()),                    computeName(NT_NS, NT_FILE));
		jcrCacheMap.put(new Integer(NT_FOLDER.hashCode()),                  computeName(NT_NS, NT_FOLDER));
		jcrCacheMap.put(new Integer(NT_FROZENNODE.hashCode()),              computeName(NT_NS, NT_FROZENNODE));
		jcrCacheMap.put(new Integer(NT_HIERARCHYNODE.hashCode()),           computeName(NT_NS, NT_HIERARCHYNODE));
		jcrCacheMap.put(new Integer(NT_LINKEDFILE.hashCode()),              computeName(NT_NS, NT_LINKEDFILE));
		jcrCacheMap.put(new Integer(NT_NODETYPE.hashCode()),                computeName(NT_NS, NT_NODETYPE));
		jcrCacheMap.put(new Integer(NT_PROPERTYDEFINITION.hashCode()),      computeName(NT_NS, NT_PROPERTYDEFINITION));
		jcrCacheMap.put(new Integer(NT_QUERY.hashCode()),                   computeName(NT_NS, NT_QUERY));
		jcrCacheMap.put(new Integer(NT_RESOURCE.hashCode()),                computeName(NT_NS, NT_RESOURCE));
		jcrCacheMap.put(new Integer(NT_UNSTRUCTURED.hashCode()),            computeName(NT_NS, NT_UNSTRUCTURED));
		jcrCacheMap.put(new Integer(NT_VERSION.hashCode()),                 computeName(NT_NS, NT_VERSION));
		jcrCacheMap.put(new Integer(NT_VERSIONEDCHILD.hashCode()),          computeName(NT_NS, NT_VERSIONEDCHILD));
		jcrCacheMap.put(new Integer(NT_VERSIONHISTORY.hashCode()),          computeName(NT_NS, NT_VERSIONHISTORY));
		jcrCacheMap.put(new Integer(NT_VERSIONLABELS.hashCode()),           computeName(NT_NS, NT_VERSIONLABELS));
	}

	public String getJCR_AUTOCREATED() {
		return resolveName(JCR_NS, JCR_AUTOCREATED);
	}

	/**
	 * @return Returns the JCR_BASEVERSION.
	 */
	public String getJCR_BASEVERSION() {
		return resolveName(JCR_NS, JCR_BASEVERSION);
	}

	/**
	 * @return Returns the JCR_CHILD.
	 */
	public String getJCR_CHILD() {
		return resolveName(JCR_NS, JCR_CHILD);
	}

	/**
	 * @return Returns the JCR_CHILDNODEDEFINITION.
	 */
	public String getJCR_CHILDNODEDEFINITION() {
		return resolveName(JCR_NS, JCR_CHILDNODEDEFINITION);
	}

	/**
	 * @return Returns the JCR_CONTENT.
	 */
	public String getJCR_CONTENT() {
		return resolveName(JCR_NS, JCR_CONTENT);
	}

	/**
	 * @return Returns the JCR_CREATED.
	 */
	public String getJCR_CREATED() {
		return resolveName(JCR_NS, JCR_CREATED);
	}

	/**
	 * @return Returns the JCR_DATA.
	 */
	public String getJCR_DATA() {
		return resolveName(JCR_NS, JCR_DATA);
	}

	/**
	 * @return Returns the JCR_DEFAULTPRIMARYTYPE.
	 */
	public String getJCR_DEFAULTPRIMARYTYPE() {
		return resolveName(JCR_NS, JCR_DEFAULTPRIMARYTYPE);
	}

	/**
	 * @return Returns the JCR_DEFAULTVALUES.
	 */
	public String getJCR_DEFAULTVALUES() {
		return resolveName(JCR_NS, JCR_DEFAULTVALUES);
	}

	/**
	 * @return Returns the JCR_ENCODING.
	 */
	public String getJCR_ENCODING() {
		return resolveName(JCR_NS, JCR_ENCODING);
	}

	/**
	 * @return Returns the JCR_FROZENMIXINTYPES.
	 */
	public String getJCR_FROZENMIXINTYPES() {
		return resolveName(JCR_NS, JCR_FROZENMIXINTYPES);
	}

	/**
	 * @return Returns the JCR_FROZENNODE.
	 */
	public String getJCR_FROZENNODE() {
		return resolveName(JCR_NS, JCR_FROZENNODE);
	}

	/**
	 * @return Returns the JCR_FROZENPRIMARYTYPE.
	 */
	public String getJCR_FROZENPRIMARYTYPE() {
		return resolveName(JCR_NS, JCR_FROZENPRIMARYTYPE);
	}

	/**
	 * @return Returns the JCR_FROZENUUID.
	 */
	public String getJCR_FROZENUUID() {
		return resolveName(JCR_NS, JCR_FROZENUUID);
	}

	/**
	 * @return Returns the JCR_HASORDERABLECHILDNODES.
	 */
	public String getJCR_HASORDERABLECHILDNODES() {
		return resolveName(JCR_NS, JCR_HASORDERABLECHILDNODES);
	}

	/**
	 * @return Returns the JCR_ISCHECKEDOUT.
	 */
	public String getJCR_ISCHECKEDOUT() {
		return resolveName(JCR_NS, JCR_ISCHECKEDOUT);
	}

	/**
	 * @return Returns the JCR_ISMIXIN.
	 */
	public String getJCR_ISMIXIN() {
		return resolveName(JCR_NS, JCR_ISMIXIN);
	}

	/**
	 * @return Returns the JCR_LANGUAGE.
	 */
	public String getJCR_LANGUAGE() {
		return resolveName(JCR_NS, JCR_LANGUAGE);
	}

	/**
	 * @return Returns the JCR_LASTMODIFIED.
	 */
	public String getJCR_LASTMODIFIED() {
		return resolveName(JCR_NS, JCR_LASTMODIFIED);
	}

	/**
	 * @return Returns the JCR_LOCKISDEEP.
	 */
	public String getJCR_LOCKISDEEP() {
		return resolveName(JCR_NS, JCR_LOCKISDEEP);
	}

	/**
	 * @return Returns the JCR_LOCKOWNER.
	 */
	public String getJCR_LOCKOWNER() {
		return resolveName(JCR_NS, JCR_LOCKOWNER);
	}

	/**
	 * @return Returns the JCR_MANDATORY.
	 */
	public String getJCR_MANDATORY() {
		return resolveName(JCR_NS, JCR_MANDATORY);
	}

	/**
	 * @return Returns the JCR_MERGEFAILED.
	 */
	public String getJCR_MERGEFAILED() {
		return resolveName(JCR_NS, JCR_MERGEFAILED);
	}

	/**
	 * @return Returns the JCR_MIMETYPE.
	 */
	public String getJCR_MIMETYPE() {
		return resolveName(JCR_NS, JCR_MIMETYPE);
	}

	/**
	 * @return Returns the JCR_MIXINTYPES.
	 */
	public String getJCR_MIXINTYPES() {
		return resolveName(JCR_NS, JCR_MIXINTYPES);
	}

	/**
	 * @return Returns the JCR_MULTIPLE.
	 */
	public String getJCR_MULTIPLE() {
		return resolveName(JCR_NS, JCR_MULTIPLE);
	}

	/**
	 * @return Returns the JCR_NAME.
	 */
	public String getJCR_NAME() {
		return resolveName(JCR_NS, JCR_NAME);
	}

	/**
	 * @return Returns the JCR_NODETYPENAME.
	 */
	public String getJCR_NODETYPENAME() {
		return resolveName(JCR_NS, JCR_NODETYPENAME);
	}

	/**
	 * @return Returns the JCR_ONPARENTVERSION.
	 */
	public String getJCR_ONPARENTVERSION() {
		return resolveName(JCR_NS, JCR_ONPARENTVERSION);
	}

	/**
	 * @return Returns the JCR_PATH.
	 */
	public String getJCR_PATH() {
		return resolveName(JCR_NS, JCR_PATH);
	}

	/**
	 * @return Returns the JCR_PREDECESSORS.
	 */
	public String getJCR_PREDECESSORS() {
		return resolveName(JCR_NS, JCR_PREDECESSORS);
	}

	/**
	 * @return Returns the JCR_PRIMARYITEMNAME.
	 */
	public String getJCR_PRIMARYITEMNAME() {
		return resolveName(JCR_NS, JCR_PRIMARYITEMNAME);
	}

	/**
	 * @return Returns the JCR_PRIMARYTYPE.
	 */
	public String getJCR_PRIMARYTYPE() {
		return resolveName(JCR_NS, JCR_PRIMARYTYPE);
	}

	/**
	 * @return Returns the JCR_PROPERTYDEFINITION.
	 */
	public String getJCR_PROPERTYDEFINITION() {
		return resolveName(JCR_NS, JCR_PROPERTYDEFINITION);
	}

	/**
	 * @return Returns the JCR_PROTECTED.
	 */
	public String getJCR_PROTECTED() {
		return resolveName(JCR_NS, JCR_PROTECTED);
	}

	/**
	 * @return Returns the JCR_REQUIREDPRIMARYTYPES.
	 */
	public String getJCR_REQUIREDPRIMARYTYPES() {
		return resolveName(JCR_NS, JCR_REQUIREDPRIMARYTYPES);
	}

	/**
	 * @return Returns the JCR_REQUIREDTYPE.
	 */
	public String getJCR_REQUIREDTYPE() {
		return resolveName(JCR_NS, JCR_REQUIREDTYPE);
	}

	/**
	 * @return Returns the JCR_ROOTVERSION.
	 */
	public String getJCR_ROOTVERSION() {
		return resolveName(JCR_NS, JCR_ROOTVERSION);
	}

	/**
	 * @return Returns the JCR_SAMENAMESIBLINGS.
	 */
	public String getJCR_SAMENAMESIBLINGS() {
		return resolveName(JCR_NS, JCR_SAMENAMESIBLINGS);
	}

	/**
	 * @return Returns the JCR_SCORE.
	 */
	public String getJCR_SCORE() {
		return resolveName(JCR_NS, JCR_SCORE);
	}

	/**
	 * @return Returns the JCR_STATEMENT.
	 */
	public String getJCR_STATEMENT() {
		return resolveName(JCR_NS, JCR_STATEMENT);
	}

	/**
	 * @return Returns the JCR_SUCCESSORS.
	 */
	public String getJCR_SUCCESSORS() {
		return resolveName(JCR_NS, JCR_SUCCESSORS);
	}

	/**
	 * @return Returns the JCR_SUPERTYPES.
	 */
	public String getJCR_SUPERTYPES() {
		return resolveName(JCR_NS, JCR_SUPERTYPES);
	}

	/**
	 * @return Returns the JCR_SYSTEM.
	 */
	public String getJCR_SYSTEM() {
		return resolveName(JCR_NS, JCR_SYSTEM);
	}

	/**
	 * @return Returns the JCR_UUID.
	 */
	public String getJCR_UUID() {
		return resolveName(JCR_NS, JCR_UUID);
	}

	/**
	 * @return Returns the JCR_VALUECONSTRAINTS.
	 */
	public String getJCR_VALUECONSTRAINTS() {
		return resolveName(JCR_NS, JCR_VALUECONSTRAINTS);
	}

	/**
	 * @return Returns the JCR_VERSIONABLEUUID.
	 */
	public String getJCR_VERSIONABLEUUID() {
		return resolveName(JCR_NS, JCR_VERSIONABLEUUID);
	}

	/**
	 * @return Returns the JCR_VERSIONHISTORY.
	 */
	public String getJCR_VERSIONHISTORY() {
		return resolveName(JCR_NS, JCR_VERSIONHISTORY);
	}

	/**
	 * @return Returns the JCR_VERSIONLABELS.
	 */
	public String getJCR_VERSIONLABELS() {
		return resolveName(JCR_NS, JCR_VERSIONLABELS);
	}

	/**
	 * @return Returns the JCR_VERSIONSTORAGE.
	 */
	public String getJCR_VERSIONSTORAGE() {
		return resolveName(JCR_NS, JCR_VERSIONSTORAGE);
	}

	/**
	 * @return Returns the MIX_LOCKABLE.
	 */
	public String getMIX_LOCKABLE() {
		return resolveName(MIX_NS, MIX_LOCKABLE);
	}

	/**
	 * @return Returns the MIX_REFERENCEABLE.
	 */
	public String getMIX_REFERENCEABLE() {
		return resolveName(MIX_NS, MIX_REFERENCEABLE);
	}

	/**
	 * @return Returns the MIX_VERSIONABLE.
	 */
	public String getMIX_VERSIONABLE() {
		return resolveName(MIX_NS, MIX_VERSIONABLE);
	}

	/**
	 * @return Returns the NT_BASE.
	 */
	public String getNT_BASE() {
		return resolveName(NT_NS, NT_BASE);
	}

	/**
	 * @return Returns the NT_CHILDNODEDEFINITION.
	 */
	public String getNT_CHILDNODEDEFINITION() {
		return resolveName(NT_NS, NT_CHILDNODEDEFINITION);
	}

	/**
	 * @return Returns the NT_FILE.
	 */
	public String getNT_FILE() {
		return resolveName(NT_NS, NT_FILE);
	}

	/**
	 * @return Returns the NT_FOLDER.
	 */
	public String getNT_FOLDER() {
		return resolveName(NT_NS, NT_FOLDER);
	}

	/**
	 * @return Returns the NT_FROZENNODE.
	 */
	public String getNT_FROZENNODE() {
		return resolveName(NT_NS, NT_FROZENNODE);
	}

	/**
	 * @return Returns the NT_HIERARCHYNODE.
	 */
	public String getNT_HIERARCHYNODE() {
		return resolveName(NT_NS, NT_HIERARCHYNODE);
	}

	/**
	 * @return Returns the NT_LINKEDFILE.
	 */
	public String getNT_LINKEDFILE() {
		return resolveName(NT_NS, NT_LINKEDFILE);
	}

	/**
	 * @return Returns the NT_NODETYPE.
	 */
	public String getNT_NODETYPE() {
		return resolveName(NT_NS, NT_NODETYPE);
	}

	/**
	 * @return Returns the NT_PROPERTYDEFINITION.
	 */
	public String getNT_PROPERTYDEFINITION() {
		return resolveName(NT_NS, NT_PROPERTYDEFINITION);
	}

	/**
	 * @return Returns the NT_QUERY.
	 */
	public String getNT_QUERY() {
		return resolveName(NT_NS, NT_QUERY);
	}

	/**
	 * @return Returns the NT_RESOURCE.
	 */
	public String getNT_RESOURCE() {
		return resolveName(NT_NS, NT_RESOURCE);
	}

	/**
	 * @return Returns the NT_UNSTRUCTURED.
	 */
	public String getNT_UNSTRUCTURED() {
		return resolveName(NT_NS, NT_UNSTRUCTURED);
	}

	/**
	 * @return Returns the NT_VERSION.
	 */
	public String getNT_VERSION() {
		return resolveName(NT_NS, NT_VERSION);
	}

	/**
	 * @return Returns the NT_VERSIONEDCHILD.
	 */
	public String getNT_VERSIONEDCHILD() {
		return resolveName(NT_NS, NT_VERSIONEDCHILD);
	}

	/**
	 * @return Returns the NT_VERSIONHISTORY.
	 */
	public String getNT_VERSIONHISTORY() {
		return resolveName(NT_NS, NT_VERSIONHISTORY);
	}

	/**
	 * @return Returns the NT_VERSIONLABELS.
	 */
	public String getNT_VERSIONLABELS() {
		return resolveName(NT_NS, NT_VERSIONLABELS);
	}
}
