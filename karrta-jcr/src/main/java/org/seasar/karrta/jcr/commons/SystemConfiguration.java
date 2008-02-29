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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * system configuration.
 * 
 * @author yosukehara
 * 
 */
public class SystemConfiguration {
    private static final Log logger_ = LogFactory.getLog(SystemConfiguration.class);
    private static final String PROP_MODE_KEY_NAME     = "karrta.jcr.mode";
    private static final String PROP_MODE_DEFAULT_NAME = "default.";

    /** system config list. */
    static List<Map<String, String>> systemConfigList_;

    /** configuration file path */
    String configurationName_ = "";

    /**
     * set configuration name.
     * 
     * @param configurationName
     */
    public void setConfigurationName(String configurationName) {
        this.configurationName_ = configurationName;
    }

    /**
     * initialize
     * 
     */
    public synchronized void initialize() throws ConfigurationException{
        systemConfigList_ = new ArrayList<Map<String, String>>();

        Configuration config = new PropertiesConfiguration(this.configurationName_);
        String mode = config.getString(PROP_MODE_KEY_NAME) + ".";

        Iterator<?> i = config.getKeys();
        String key, value;

        while (i.hasNext()) {
            key = (String) i.next();
            value = config.getString(key);

            if (key.indexOf(mode) == 0 
             || key.indexOf(PROP_MODE_DEFAULT_NAME) == 0) {

                String storeKey = key.indexOf(mode) > -1 ? key.substring(mode.length()) : key;

                Map<String, String> map = new HashMap<String, String>();
                map.put(storeKey, value);

                systemConfigList_.add(map);

                logger_.debug("::: SystemConfiguration:[" + map + "]");
            }
        }
    }

    /**
     * Get configuration value
     * 
     * @param key
     * @return
     */
    public static String getValue(String key) {
        if (systemConfigList_ == null) return null;

        String result = null;
        for (Map<String, String> config : systemConfigList_) {
            if (config.containsKey(key)) {
                result = config.get(key);
                break;
            }
        }
        return result;
    }

    /**
     * Get configuration value with string.
     * 
     * @param key
     * @param string
     * @return
     */
    public static String getValueWithString(String key, String string) {
        return getValue(key) + (string == null ? "" : string);
    }
}
