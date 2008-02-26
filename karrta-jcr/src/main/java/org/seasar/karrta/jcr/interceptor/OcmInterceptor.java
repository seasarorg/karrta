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
package org.seasar.karrta.jcr.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.query.Query;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.karrta.jcr.annotation.Ocm;
import org.seasar.karrta.jcr.exception.InvalidArgumentException;
import org.seasar.karrta.jcr.exception.InvalidMethodNameException;
import org.seasar.karrta.jcr.ocm.ObjectContentManagerFactory;

/**
 * OCM Interceptor.
 * 
 * @author yosukehara
 * 
 */
public class OcmInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;

    private static final String TYPE_CREATE = "create";
    private static final String TYPE_UPDATE = "update";
    private static final String TYPE_REMOVE = "remove";
    private static final String TYPE_FIND   = "find";
    
    private static final int TYPE_CREATE_CODE = 1;
    private static final int TYPE_UPDATE_CODE = 2;
    private static final int TYPE_REMOVE_CODE = 3;
    private static final int TYPE_FIND_CODE   = 4;
    
    /** logger */
    private static final Log logger_ = LogFactory.getLog(OcmInterceptor.class);

    /** ocm factory */
    private ObjectContentManagerFactory ocmFactory;

    public void setObjectContentManagerFactory(ObjectContentManagerFactory ocmFactory) {
        this.ocmFactory = ocmFactory;
    }

    /*
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start, end;
        start = System.currentTimeMillis();

        String className = invocation.getClass().getName();
        className = className.substring(0, className.indexOf("$$"));

        Ocm ocmAnnotation = Class.forName(className).getAnnotation(Ocm.class);
        Class bean = ocmAnnotation.bean();

        this.ocmFactory.addMappingClasses(bean);
        boolean canInvoke = false;
        
        Object[] args = invocation.getArguments();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg != null && 
                    (arg.getClass().getName().indexOf(bean.getName()) > -1
                  || arg.getClass().getName().indexOf(Query.class.getName()) > -1)) {
                    
                    logger_.debug("::: arg:[" + arg.getClass().getName() + "] :::");
                    logger_.debug("::: arg:[" + arg.toString()           + "] :::");
                    canInvoke = true;
                }
            }
        }
        int methodType = this.checkMethod(invocation.getMethod().getName());
        if (methodType == 0) {
            throw new InvalidMethodNameException("");
        }
        if (! canInvoke) {
            throw new InvalidArgumentException("");
        }
        
        //ObjectContentManager ocm = ocmFactory.getObjectContentManager();
        switch(methodType) {
            case TYPE_CREATE_CODE:
                //ocm.insert(args[0]);
                //ocm.save();
                break;
            case TYPE_UPDATE_CODE:
                //ocm.update(args[0]);
                //ocm.save();
                break;
            case TYPE_REMOVE_CODE:
                //ocm.remove(args[0]);
                //ocm.save();
                break;
            case TYPE_FIND_CODE:
                break;
        }
        
        end = System.currentTimeMillis();
        logger_.debug("\n::::: processing time:[" + (end - start) + "ms] :::::\n");

        return null;
    }
    
    /**
     * check mothod.
     * 
     * @param methodName
     * @return
     */
    private int checkMethod(String methodName) {
        int result = 
            methodName.indexOf(TYPE_CREATE) > -1 ? TYPE_CREATE_CODE :
            methodName.indexOf(TYPE_UPDATE) > -1 ? TYPE_UPDATE_CODE :
            methodName.indexOf(TYPE_REMOVE) > -1 ? TYPE_REMOVE_CODE :
            methodName.indexOf(TYPE_FIND)   > -1 ? TYPE_FIND_CODE   :
                                                   0;
        return result;
    }

}
