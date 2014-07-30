/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.persistence.filecfg.decorator;

import org.metastopheles.MetaDataDecorator;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

import java.util.Map.Entry;
import java.util.Properties;

public class FileCfgPropertyDecorator implements MetaDataDecorator<PropertyMetaData> {

    private static enum META_DATA_TYPE {
        IGNORED_PROPERTY, VISIBLE_PROPERTY, HIDDEN_PROPERTY, ENABLED_PROPERTY, DISABLED_PROPERTY, ORDER_PROPERTY
    }

    // ----------------------------------------------------------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------------------------------------------------------

    public FileCfgPropertyDecorator() {
    }

    // ----------------------------------------------------------------------------------------------------------------------
    // MetaDataDecorator Implementation
    // ----------------------------------------------------------------------------------------------------------------------

    public void decorate(PropertyMetaData propertyMetaData) {
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
        Properties properties = propertyMetaData.getBeanMetaData().getFacet(FileCfgFacet.FACET_KEY).getProperties();
        if (properties != null) {
            for (Entry<Object, Object> e : properties.entrySet()) {
                if (propertyMetaData.getPropertyDescriptor().getName().equals(e.getKey().toString())) {
                    configureProperty(facet, e.getKey().toString(), e.getValue().toString());
                }
            }
        }
    }

    private void configureProperty(WicketopiaPropertyFacet facet, String property, String value) {
        String[] items = value.split(",");
        for (String item : items) {
            item = item.trim(); // strip white-space

            ContextPredicate predicate;
            switch (decodeItem(item)) {
                case IGNORED_PROPERTY:
                    facet.setIgnored(true);
                    break;
                case ENABLED_PROPERTY:
                    predicate = getContextPredicate(item);
                    facet.setEnabled(predicate, true);
                    break;
                case DISABLED_PROPERTY:
                    predicate = getContextPredicate(item);
                    facet.setEnabled(predicate, false);
                    break;
                case VISIBLE_PROPERTY:
                    predicate = getContextPredicate(item);
                    facet.setVisible(predicate, true);
                    break;
                case HIDDEN_PROPERTY:
                    predicate = getContextPredicate(item);
                    facet.setVisible(predicate, false);
                    break;
                case ORDER_PROPERTY:
                    String[] ord = item.split(FileCfgBeanDecorator.ORDER_QUAL);
                    if (ord.length != 2) {
                    } else {
                        try {
                            int order = Integer.parseInt(ord[1]);
                            facet.setOrder(order);
                        } catch (NumberFormatException nfe) {
                        }
                    }
                    break;
                default:
            }
        }
    }

    private ContextPredicate getContextPredicate(String item) {
        int predicateOffset = item.lastIndexOf('/');
        if (predicateOffset > 0) {
            String predicate = item.substring(predicateOffset + 1);
            if (predicate.length() > 0) {
                return Context.whereContextNameIn(predicate);
            }
        }
        return Context.ALL_CONTEXTS;
    }

    private META_DATA_TYPE decodeItem(String item) {
        if (item.equals(FileCfgBeanDecorator.IGNORED)) {
            return META_DATA_TYPE.IGNORED_PROPERTY;
        } else if (item.startsWith(FileCfgBeanDecorator.ENABLED)) {
            return META_DATA_TYPE.ENABLED_PROPERTY;
        } else if (item.startsWith(FileCfgBeanDecorator.DISABLED)) {
            return META_DATA_TYPE.DISABLED_PROPERTY;
        } else if (item.startsWith(FileCfgBeanDecorator.VISIBLE)) {
            return META_DATA_TYPE.VISIBLE_PROPERTY;
        } else if (item.startsWith(FileCfgBeanDecorator.HIDDEN)) {
            return META_DATA_TYPE.HIDDEN_PROPERTY;
        } else if (item.startsWith(FileCfgBeanDecorator.ORDER)) {
            return META_DATA_TYPE.ORDER_PROPERTY;
        }

        throw new IllegalArgumentException("Invalid property specification: " + item);
    }
    // ----------------------------------------------------------------------------------------------------------------------
    // Other Methods
    // ----------------------------------------------------------------------------------------------------------------------

}
