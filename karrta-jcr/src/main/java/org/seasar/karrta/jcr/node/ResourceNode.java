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

import java.io.InputStream;
import java.util.Calendar;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;

/**
 * jcr resource node.
 * 
 * @author yosukehara
 * 
 */
public class ResourceNode {

    @Field(jcrName = "jcr:mimeType")
    private String mimeType;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Field(jcrName = "jcr:data")
    private InputStream data;

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    @Field(jcrName = "jcr:lastModified")
    private Calendar lastModified;

    public Calendar getLastModified() {
        return lastModified;
    }

    public void setLastModified(Calendar lastModified) {
        this.lastModified = lastModified;
    }

    /*
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
