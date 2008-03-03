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
    private EventListener listener_;

    public EventListener getListener() {
        return listener_;
    }

    public void setListner(EventListener listener) {
        this.listener_ = listener;
    }

    /** event types */
    private int eventTypes_;

    public int getEventTypes() {
        return eventTypes_;
    }

    public void setEventTypes(int eventTypes) {
        this.eventTypes_ = eventTypes;
    }

    /** absolute path */
    private String absPath_;

    public String getAbsPath() {
        return absPath_;
    }

    public void setAbsPath(String absPath) {
        this.absPath_ = absPath;
    }

    /** is deep */
    private boolean isDeep_;

    public boolean isDeep() {
        return isDeep_;
    }

    public void setIsDeep(boolean isDeep) {
        this.isDeep_ = isDeep;
    }

    /** uuids */
    private String[] uuids_;

    public String[] getUuids() {
        return uuids_;
    }

    public void setUuids(String[] uuids) {
        this.uuids_ = uuids;
    }

    /** node type names */
    private String[] nodeTypeNames_;

    public String[] getNodeTypeNames() {
        return nodeTypeNames_;
    }
    
    public void setNodeTypeNames(String[] nodeTypeNames) {
        this.nodeTypeNames_ = nodeTypeNames;
    }

    /** no local */
    private boolean noLocal_;

    public boolean getNoLocal() {
        return noLocal_;
    }
    
    public void setNoLocal(boolean noLocal) {
        this.noLocal_ = noLocal;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
