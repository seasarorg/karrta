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
package org.seasar.karrta.jcr.register;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.autoregister.ClassPattern;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * jcr node component auto register.
 * 
 * @author yosukehara
 * 
 */
public class JcrObservationComponentRegister extends JcrComponentRegister {
    private Log logger_ = LogFactory.getLog(JcrObservationComponentRegister.class);

    public JcrObservationComponentRegister() {
    }
    
    /** jcr node classes */
    private Set<Object> observationClasses_ = new HashSet<Object>();

    public Set<Object> getJcrObservations() {
        return observationClasses_;
    }

    /*
     * @see org.seasar.framework.container.autoregister.AbstractComponentAutoRegister#processClass(
     *   java.lang.String, java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void processClass(final String packageName, final String shortClassName) {
        if (isIgnore(packageName, shortClassName)) {
            return;
        }

        for (int i = 0; i < getClassPatternSize(); ++i) {
            final ClassPattern cp = getClassPattern(i);
            if (cp.isAppliedPackageName(packageName) && cp.isAppliedShortClassName(shortClassName)) {
                
                try {
                    Class clazz = Class.forName(ClassUtil.concatName(packageName, shortClassName));
                    S2Container container = SingletonS2ContainerFactory.getContainer();
                    logger_.debug("::: observation-event-listener:[" + container.getComponent(clazz) + "]");
                    
                    this.observationClasses_.add(container.getComponent(clazz));
                    
                } catch(ClassNotFoundException e) {
                	throw new JcrRepositoryRuntimeException("", e);
                }
                return;
            }
        }
    }
    
}
