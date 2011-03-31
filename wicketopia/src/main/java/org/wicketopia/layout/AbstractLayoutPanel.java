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
import org.wicketopia.metadata.WicketopiaPropertyFacet;

import java.util.Iterator;
import java.util.List;

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

    private static List<String> filter(Class<?> beanType, List<String> propertyNames, Context context)
    {
        BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetaData(beanType);
        for (Iterator<String> iterator = propertyNames.iterator(); iterator.hasNext();)
        {
            String propertyName = iterator.next();
            PropertyMetaData propertyMetaData = beanMetaData.getPropertyMetaData(propertyName);
            final WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
            if (!facet.isIgnored() && facet.isVisible(context))
            {
                iterator.remove();
            }
        }
        return propertyNames;
    }

    private static int getOrder(BeanMetaData beanMetaData, String propertyName)
    {
        return WicketopiaPropertyFacet.get(beanMetaData.getPropertyMetaData(propertyName)).getOrder();
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractLayoutPanel(String id, Class<T> beanType, Context context, PropertyComponentFactory<T> componentFactory)
    {
        this(id, beanType, context, componentFactory, WicketopiaPlugin.get().getVisibleProperties(beanType, context));
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
        this(id, beanType, context, componentFactory, WicketopiaPlugin.get().getVisibleProperties(beanType, context, propertyNames));
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public List<String> getPropertyNames()
    {
        return propertyNames;
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
}
