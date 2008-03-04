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

import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * book node added event listener.
 * 
 * @author yosukehara
 *
 */
public class BookNodeAddedEventListener implements EventListener {
    private static final Log logger_ = LogFactory.getLog(BookNodeAddedEventListener.class);
    
    /*
     * @see javax.jcr.observation.EventListener#onEvent(javax.jcr.observation.EventIterator)
     */
    public void onEvent(EventIterator events) {
        
        logger_.debug("::: #node-added:start :::");
        
        while (events.hasNext()) {
            Event event = events.nextEvent();            
            try {
                logger_.debug("::: #node-added: Type:[" + event.getType() + "], Path:[" + event.getPath() + "]");

                // processing...
                
            } catch (RepositoryException e) {
                throw new JcrRepositoryRuntimeException("",e);
            }
        }
    }

}
