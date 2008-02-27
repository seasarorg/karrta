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
    /** logger */
    private static final Log logger_ = LogFactory.getLog(JcrUtils.class);

    /**
     * dump.
     * 
     * @param node
     * @throws JcrRepositoryRuntimeException
     */
    public static void dump(Node node) throws JcrRepositoryRuntimeException {
        try {
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
                        logger_.debug(property.getPath());
                        System.out.println(" - [" + values[i].getString() + "]");
                    }
                } else {
                    logger_.debug(property.getPath());

                    switch (property.getType()) {
                        case PropertyType.STRING:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_STRING);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.BINARY:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_BINARY);
                            logger_.debug("]");
                            break;
                        case PropertyType.LONG:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_LONG);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.DOUBLE:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_DOUBLE);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.DATE:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_DATE);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.BOOLEAN:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_BOOLEAN);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.NAME:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_NAME);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.PATH:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_PATH);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.REFERENCE:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_REFERENCE);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                        case PropertyType.UNDEFINED:
                            logger_.debug(":[");
                            logger_.debug(PropertyType.TYPENAME_UNDEFINED);
                            logger_.debug("]");
                            logger_.debug(" = " + property.getString());
                            break;
                    }
                }
            }
            NodeIterator nodes = node.getNodes();
            while (nodes.hasNext()) {
                dump(nodes.nextNode());
            }
        } catch (RepositoryException e) {
            throw new JcrRepositoryRuntimeException("", e);
        }
    }
}
