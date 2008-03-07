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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import org.seasar.framework.container.autoregister.ClassPattern;
import org.seasar.framework.util.ClassUtil;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * jcr node component auto register.
 * 
 * @author yosukehara
 * 
 */
public class JcrNodeComponentAutoRegister extends JcrComponentRegister {
    private Log logger_ = LogFactory.getLog(JcrNodeComponentAutoRegister.class);

    /** jcr node classes */
    private List<Class> jcrNodeClasses = new ArrayList<Class>();

    public List<Class> getJcrNodeClasses() {
        return jcrNodeClasses;
    }

    /*
     * @see org.seasar.framework.container.autoregister.AbstractComponentAutoRegister#processClass(
     *   java.lang.String, java.lang.String)
     */
    @Override
    public void processClass(final String packageName, final String shortClassName) {
        if (isIgnore(packageName, shortClassName)) return;

        for (int i = 0; i < getClassPatternSize(); ++i) {
            final ClassPattern cp = getClassPattern(i);
            if (cp.isAppliedPackageName(packageName) && cp.isAppliedShortClassName(shortClassName)) {
                try {
                    Class clazz = Class.forName(ClassUtil.concatName(packageName, shortClassName));

                    boolean hasNodeAnnotation = false;
                    Annotation[] annotations = clazz.getAnnotations();
                    for (Annotation a : annotations) {
                        if (a instanceof Node) {
                            hasNodeAnnotation = true;
                            break;
                        }
                    }
                    if (!hasNodeAnnotation) continue;

                    this.register(ClassUtil.concatName(packageName, shortClassName));
                    this.jcrNodeClasses.add(clazz);
                    logger_.debug("::: jcrNodeClass:[" + clazz.getName() + "]");

                } catch (ClassNotFoundException e) {
                    throw new JcrRepositoryRuntimeException("", e);
                }
                return;
            }
        }
    }

}
