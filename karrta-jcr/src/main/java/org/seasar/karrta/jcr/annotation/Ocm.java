package org.seasar.karrta.jcr.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ocm annotation.
 * 
 * @author yosukehara
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Ocm {
    /**
     * bean attribute.
     * 
     * @return
     */
    Class bean();
}
