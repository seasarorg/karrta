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
package org.seasar.karrta.jcr.commons;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.karrta.jcr.exception.JcrRepositoryRuntimeException;

/**
 * jcr utils.
 * 
 * @author yosukehara
 *
 */
public class JcrUtils {
    private static final Log logger_ = LogFactory.getLog(JcrUtils.class);

    /**
     * dump.
     * 
     * @param node
     * @throws JcrRepositoryRuntimeException
     */
    public static void dump(Session session) throws JcrRepositoryRuntimeException {
        try {
            dump(session.getRootNode());

        } catch (RepositoryException e) {
            throw new JcrRepositoryRuntimeException("", e);
        } finally {
            session.logout();
        }
    }
    
    private static void dump(Node node) throws RepositoryException {
        logger_.debug(node.getPath());

        if (node.getName().equals("jcr:system")) {
            return;
        }
        PropertyIterator properties = node.getProperties();

        while (properties.hasNext()) {
            Property property = properties.nextProperty();
            if (property.getDefinition().isMultiple()) {
                Value[] values = property.getValues();
                for (int i = 0; i < values.length; i++) {
                    logger_.debug(property.getPath() + " - [" + values[i].getString() + "]");
                }
            } else {
                switch (property.getType()) {
                    case PropertyType.STRING:   logger_.debug(concat(PropertyType.TYPENAME_STRING, property));    break;
                    case PropertyType.BINARY:   logger_.debug(concat(PropertyType.TYPENAME_BINARY, property));    break;
                    case PropertyType.LONG:     logger_.debug(concat(PropertyType.TYPENAME_LONG, property));      break;
                    case PropertyType.DOUBLE:   logger_.debug(concat(PropertyType.TYPENAME_DOUBLE, property));    break;
                    case PropertyType.DATE:     logger_.debug(concat(PropertyType.TYPENAME_DATE, property));      break;
                    case PropertyType.BOOLEAN:  logger_.debug(concat(PropertyType.TYPENAME_BOOLEAN, property));   break;
                    case PropertyType.NAME:     logger_.debug(concat(PropertyType.TYPENAME_NAME, property));      break;
                    case PropertyType.PATH:     logger_.debug(concat(PropertyType.TYPENAME_PATH, property));      break;
                    case PropertyType.REFERENCE:logger_.debug(concat(PropertyType.TYPENAME_REFERENCE, property)); break;
                    case PropertyType.UNDEFINED:logger_.debug(concat(PropertyType.TYPENAME_UNDEFINED, property)); break;
                }
            }
        }
        NodeIterator nodes = node.getNodes();
        while (nodes.hasNext()) {
            dump(nodes.nextNode());
        }
    }
    
    /**
     * concat.
     * 
     * @param path
     * @param typeName
     * @param property
     * @return
     */
    private static String concat(String typeName, Property property) throws RepositoryException {
        StringBuilder buf = new StringBuilder();
        buf.append(property.getPath()).append(":[").append(typeName).append(" = ").append(property.getString());
        return buf.toString();
    }
}
