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

package org.wicketopia.factory;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.context.Context;

/**
 * @ince 1.0
 */
public class PropertyViewerComponentFactory<T> extends AbstractPropertyComponentFactory<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PropertyViewerComponentFactory(Class<T> beanType)
    {
        super(beanType);
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyComponentFactory Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public Component createPropertyComponent(String id, IModel<T> beanModel, String propertyName, Context context)
    {
        WicketopiaPlugin plugin = WicketopiaPlugin.get();
        PropertyMetaData propertyMetaData = plugin.getBeanMetaData(beanType).getPropertyMetaData(propertyName);
        return plugin.createPropertyViewer(id, propertyMetaData, new PropertyModel(beanModel, propertyName), context);
    }
}
