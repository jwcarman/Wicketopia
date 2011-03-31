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

package org.wicketopia.layout.view;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.AbstractLayoutPanel;

import java.util.List;

public abstract class BeanViewLayoutPanel<T> extends AbstractLayoutPanel<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final IModel<T> beanModel;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanViewLayoutPanel(String id, Class<T> beanType, IModel<T> beanModel, Context context, PropertyComponentFactory<T> componentFactory)
    {
        super(id, beanType, context, componentFactory);
        this.beanModel = beanModel;
    }

    protected BeanViewLayoutPanel(String id, Class<T> beanType, IModel<T> beanModel, Context context, PropertyComponentFactory<T> tPropertyComponentFactory, List<String> propertyNames)
    {
        super(id, beanType, context, tPropertyComponentFactory, propertyNames);
        this.beanModel = beanModel;
    }

    public BeanViewLayoutPanel(String id, Class<T> beanType, IModel<T> beanModel, Context context, PropertyComponentFactory<T> componentFactory, String... propertyNames)
    {
        super(id, beanType, context, componentFactory, propertyNames);
        this.beanModel = beanModel;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Component createPropertyComponent(String componentId, String propertyName)
    {
        return createPropertyComponent(componentId, beanModel, propertyName);
    }

    @Override
    protected void onDetach()
    {
        super.onDetach();
        beanModel.detach();
    }
}
