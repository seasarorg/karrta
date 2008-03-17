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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.jackrabbit.ocm.manager.collectionconverter.impl.NTCollectionConverterImpl;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Collection;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

/**
 * child node.
 * 
 * @author yosukehara
 * 
 */
@Node(jcrMixinTypes = "mix:referenceable")
public class ChildTreeNode implements Serializable {
    private static final long serialVersionUID = 1228417012218332896L;

    public ChildTreeNode() {}

    /**
     * @param id
     * @param title
     */
    public ChildTreeNode(long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * @param id
     * @param title
     * @param children
     */
    public ChildTreeNode(long id, String title, List<ChildTreeNode> children) {
        this.id = id;
        this.title = title;
        this.children = children;
    }

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
    private Date createdAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /** modified */
    @Field
    private Date modified = new Date();

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    /** children */	
    @Collection(elementClassName = ChildTreeNode.class, collectionConverter = NTCollectionConverterImpl.class)
    private List<ChildTreeNode> children = null;

    public List<ChildTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ChildTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((children == null) ? 0 : children.hashCode());
        result = prime * result
                + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result
                + ((modified == null) ? 0 : modified.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ChildTreeNode other = (ChildTreeNode) obj;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (id != other.id)
            return false;
        if (modified == null) {
            if (other.modified != null)
                return false;
        } else if (!modified.equals(other.modified))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }
    
}
