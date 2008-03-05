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

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.autoregister.AbstractComponentAutoRegister;
import org.seasar.framework.container.autoregister.ClassPattern;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * jcr node component auto register.
 * 
 * @author yosukehara
 * 
 */
public class JcrObservationComponentRegister extends AbstractComponentAutoRegister {
    private Log logger_ = LogFactory.getLog(JcrObservationComponentRegister.class);

    public JcrObservationComponentRegister() {
    }
    
    /** jcr node classes */
    private Set<Object> observationClasses_ = new HashSet<Object>();

    public Set<Object> getJcrObservations() {
        return observationClasses_;
    }
    
    /*
     * @see org.seasar.framework.container.autoregister.AbstractAutoRegister#registerAll()
     */
    @Override
    public void registerAll() {
        final File packageDir = getRootDir();
        final String[] referencePackages = this.getTargetPackages();

        for (int i = 0; i < referencePackages.length; ++i) {
            File dir = this.getPackageDir(packageDir, referencePackages[i]);
            if (dir.exists()) {
                this.traverseFileSystem(packageDir, "");
            }
        }
    }

    /**
     * get root dir.
     * 
     * @return
     */
    protected File getRootDir() {
        final String path = getContainer().getPath();
        final String[] names = StringUtil.split(path, "/");

        File file = ResourceUtil.getResourceAsFile(path);
        for (int i = 0; i < names.length; ++i) {
            file = file.getParentFile();
        }
        return file;
    }

    /**
     * get package dir.
     * 
     * @param rootDir
     * @param rootPackage
     * @return
     */
    private File getPackageDir(final File rootDir, final String rootPackage) {
        File packageDir = rootDir;
        if (rootPackage != null) {
            final String[] names = rootPackage.split("\\.");
            for (int i = 0; i < names.length; i++) {
                packageDir = new File(packageDir, names[i]);
            }
        }
        return packageDir;
    }

    /*
     * @see org.seasar.framework.container.autoregister.AbstractComponentAutoRegister#processClass(java.lang.String, java.lang.String)
     */
    @Override
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
                    //logger_.debug("::: container#getComponent:[" + container.getComponent(clazz) + "]");
                    
                    this.observationClasses_.add(container.getComponent(clazz));
                    
                } catch(ClassNotFoundException e) {
                	throw new JcrRepositoryRuntimeException("", e);
                }
                return;
            }
        }
    }
    
    /**
     * tracerse file system.
     * 
     * @param dir
     * @param packageName
     */
    private void traverseFileSystem(final File dir, final String packageName) {
        final File[] files = dir.listFiles();
        for (int i = 0; i < files.length; ++i) {
            final File file = files[i];
            final String fileName = file.getName();
            
            if (file.isDirectory()) {
                this.traverseFileSystem(
                    file, ClassUtil.concatName(packageName, fileName));

            } else if (fileName.endsWith(".class")) {
                final String shortClassName =
                    fileName.substring(0, fileName.length() - CLASS_SUFFIX.length());

                this.processClass(packageName, shortClassName);
            }
        }
    }
    
}
