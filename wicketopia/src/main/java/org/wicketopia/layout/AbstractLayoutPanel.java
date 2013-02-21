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

package org.wicketopia.layout;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;

import java.util.List;

/**
 * @since 1.0
 */
public class AbstractLayoutPanel<T> extends Panel
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Context context;
    private final Class<T> beanType;
    private final PropertyComponentFactory<T> componentFactory;
    private final List<String> propertyNames;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractLayoutPanel(String id, Class<T> beanType, Context context, PropertyComponentFactory<T> componentFactory)
    {
        this(id, beanType, context, componentFactory, Wicketopia.get().getVisibleProperties(beanType, context));
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
        this(id, beanType, context, componentFactory, Wicketopia.get().getVisibleProperties(beanType, context, propertyNames));
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Class<T> getBeanType()
    {
        return beanType;
    }

    protected PropertyComponentFactory<T> getComponentFactory()
    {
        return componentFactory;
    }

    protected Context getContext()
    {
        return context;
    }

    public List<String> getPropertyNames()
    {
        return propertyNames;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Component createPropertyComponent(String componentId, IModel<T> beanModel, String propertyName)
    {
        return getComponentFactory().createPropertyComponent(componentId, beanModel, propertyName, getContext());
    }

    protected Label createPropertyLabel(String componentId, String propertyName)
    {
        return getComponentFactory().createPropertyLabel(componentId, propertyName);
    }
}
