/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.layout;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.metadata.WicketopiaFacet;

import java.util.*;

/**
 * @since 1.0
 */
public class AbstractLayoutPanel<T> extends Panel
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final Context context;
    protected final Class<T> beanType;
    protected final PropertyComponentFactory<T> componentFactory;
    protected final List<String> propertyNames;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    private static int getOrder(BeanMetaData beanMetaData, String propertyName)
    {
        return WicketopiaFacet.get(beanMetaData.getPropertyMetaData(propertyName)).getOrder();
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractLayoutPanel(String id, Class<T> beanType, Context context, PropertyComponentFactory<T> componentFactory)
    {
        this(id, beanType, context, componentFactory, getPropertyNames(beanType));
    }

    private static List<String> getPropertyNames(Class<?> beanType)
    {
        List<PropertyMetaData> propertyMetaDataList = new LinkedList<PropertyMetaData>();
        final BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetaData(beanType);
        Set<String> propertyNames = beanMetaData.getPropertyNames();
        for (String propertyName : propertyNames)
        {
            PropertyMetaData propertyMetaData = beanMetaData.getPropertyMetaData(propertyName);
            if (!WicketopiaFacet.get(propertyMetaData).isIgnored())
            {
                propertyMetaDataList.add(propertyMetaData);
            }
        }
        WicketopiaFacet.sort(propertyMetaDataList);
        final List<String> propertyNamesList = new ArrayList<String>(propertyMetaDataList.size());
        for (PropertyMetaData propertyMetaData : propertyMetaDataList)
        {
            propertyNamesList.add(propertyMetaData.getPropertyDescriptor().getName());
        }
        return propertyNamesList;
    }

    protected AbstractLayoutPanel(String id, Class<T> beanType, Context context, PropertyComponentFactory<T> componentFactory, List<String> propertyNames)
    {
        super(id);
        this.context = context;
        this.beanType = beanType;
        this.componentFactory = componentFactory;
        this.propertyNames = propertyNames;
    }

    protected AbstractLayoutPanel(String id, Class<T> beanType, Context context, PropertyComponentFactory<T> componentFactory, String... propertyNames)
    {
        this(id, beanType, context, componentFactory, new ArrayList<String>(Arrays.asList(propertyNames)));
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Component createPropertyComponent(String componentId, IModel<T> beanModel, String propertyName)
    {
        return componentFactory.createPropertyComponent(componentId, beanModel, propertyName, context);
    }

    protected Label createPropertyLabel(String componentId, String propertyName)
    {
        return componentFactory.createPropertyLabel(componentId, propertyName);
    }

    public List<String> getPropertyNames()
    {
        return propertyNames;
    }
}
