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
package org.seasar.karrta.jcr.node;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.jackrabbit.ocm.manager.collectionconverter.impl.NTCollectionConverterImpl;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Collection;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

/**
 * parent node.
 * 
 * @author yosukehara
 * 
 */
@Node(jcrMixinTypes = "mix:referenceable")
public class ParentTreeNode extends BaseNode {

    /** path */
    @Field(path=true)
    protected String path;
    
    /** id */
    @Field(id = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /** uuid */
    @Field(uuid = true)
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /** title */
    @Field
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** created at */
    @Field
    Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /** modified */
    @Field
    Date modified;

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    /** children */
    @Collection(elementClassName = ChildTreeNode.class, collectionConverter = NTCollectionConverterImpl.class)
    List<ChildTreeNode> children = null;

    public List<ChildTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ChildTreeNode> children) {
        this.children = children;
    }
    
    public void addChild(ChildTreeNode child) {
        if (this.children == null) {
            this.children = new ArrayList<ChildTreeNode>();
        }
        this.children.add(child);
    }
    
    /*
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
