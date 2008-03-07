package org.seasar.karrta.jcr.register;

import java.io.File;

import org.seasar.framework.container.autoregister.AbstractComponentAutoRegister;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;

public abstract class JcrComponentRegister extends AbstractComponentAutoRegister {

    /*
     * (non-Javadoc)
     * @see org.seasar.framework.container.autoregister.AbstractAutoRegister#registerAll()
     */
    @Override
    public void registerAll() {
        final File packageDir = getRootDir();
        final String[] referencePackages = this.getTargetPackages();

        for (int i = 0; i < referencePackages.length; ++i) {
            File dir = getPackageDir(packageDir, referencePackages[i]);
            if (dir.exists()) {
                traverseFileSystem(packageDir, "");
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
    protected File getPackageDir(final File rootDir, final String rootPackage) {
        File packageDir = rootDir;
        if (rootPackage != null) {
            final String[] names = rootPackage.split("\\.");
            for (int i = 0; i < names.length; i++) {
                packageDir = new File(packageDir, names[i]);
            }
        }
        return packageDir;
    }
    
    /**
     * tracerse file system.
     * 
     * @param dir
     * @param packageName
     */
    protected void traverseFileSystem(final File dir, final String packageName) {
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
