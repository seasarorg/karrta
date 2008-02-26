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
package org.seasar.karrta.jcr.exception;

import org.seasar.framework.exception.SRuntimeException;

public class JcrNotMappingClassException extends SRuntimeException {
    static final long serialVersionUID = 1L;
    
    /**
     * @param messageCode
     */
    public JcrNotMappingClassException(String messageCode) {
        super(messageCode);
    }

    /**
     * @param messageCode
     * @param args
     */
    public JcrNotMappingClassException(String messageCode, Object[] args) {
        super(messageCode, args);
    }

    /**
     * @param messageCode
     * @param args
     * @param cause
     */
    public JcrNotMappingClassException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }

}
