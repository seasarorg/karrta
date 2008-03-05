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
package org.seasar.karrta.jcr.observation;

import javax.jcr.observation.EventListener;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * event listener definition.
 * 
 * @author yosukehara
 * 
 */
public class EventListenerDefinition {

    /** event listener */
    protected EventListener listener_;

    public EventListener getListener() {
        return listener_;
    }

    public void setListener(EventListener listener) {
        this.listener_ = listener;
    }

    /** event types */
    protected int eventTypes_;

    public int getEventTypes() {
        return eventTypes_;
    }

    public void setEventTypes(int eventTypes) {
        this.eventTypes_ = eventTypes;
    }

    /** absolute path */
    protected String absPath_;

    public String getAbsPath() {
        return absPath_;
    }

    public void setAbsPath(String absPath) {
        this.absPath_ = absPath;
    }

    /** is deep */
    protected boolean isDeep_;

    public boolean isDeep() {
        return isDeep_;
    }

    public void setIsDeep(boolean isDeep) {
        this.isDeep_ = isDeep;
    }

    /** uuids */
    protected String[] uuids_;

    public String[] getUuids() {
        return uuids_;
    }

    public void setUuids(String[] uuids) {
        this.uuids_ = uuids;
    }

    /** node type names */
    protected String[] nodeTypeNames_;

    public String[] getNodeTypeNames() {
        return nodeTypeNames_;
    }

    public void setNodeTypeNames(String[] nodeTypeNames) {
        this.nodeTypeNames_ = nodeTypeNames;
    }

    /** no local */
    protected boolean noLocal_;

    public boolean getNoLocal() {
        return noLocal_;
    }

    public void setNoLocal(boolean noLocal) {
        this.noLocal_ = noLocal;
    }

    /**
     * set property.
     * 
     * @param name
     * @param value
     */
    public void setProperty(String name, Object value) {
        if ("isDeep".equals(name)) {
            this.isDeep_ = ((Boolean) value).booleanValue();
            
        } else if ("getEventTypes".equals(name)) {
            this.eventTypes_ = ((Integer) value).intValue();
            
        } else if ("getAbsPath".equals(name)) {
            this.absPath_ = (String) value;
            
        } else if ("getListener".equals(name)) {
            this.listener_ = (EventListener)value;
            
        } else if ("getNoLocal".equals(name)) {
            this.noLocal_ = ((Boolean) value).booleanValue();
            
        } else if ("getNodeTypeNames".equals(name)) {
            this.nodeTypeNames_ = (String[])value;
            
        } else if ("getUuids".equals(name)) {
            this.uuids_ = (String[])value;
        }
    }
    
    /*
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
