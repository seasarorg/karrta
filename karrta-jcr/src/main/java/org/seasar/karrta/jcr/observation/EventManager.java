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

import java.util.ArrayList;
import java.util.List;

/**
 * event manager.
 * 
 * @author yosukehara
 * 
 */
public class EventManager {

    /** listener definitions */
    private List<EventListenerDefinition> listenerDefinitions_ = new ArrayList<EventListenerDefinition>();

    /**
     * get listener definitions.
     * 
     * @return
     */
    public EventListenerDefinition[] getListenerDefinitions() {
        return (
            listenerDefinitions_ == null || listenerDefinitions_.size() == 0 
            ? null
            : listenerDefinitions_.toArray(
                new EventListenerDefinition[listenerDefinitions_.size()]));
    }

    /**
     * add listener.
     * 
     * @param listener
     */
    public void addsetListenerDefinition(EventListenerDefinition listener) {
        this.listenerDefinitions_.add(listener);
    }

}
