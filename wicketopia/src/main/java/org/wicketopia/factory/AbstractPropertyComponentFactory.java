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

package org.wicketopia.factory;

import org.apache.wicket.markup.html.basic.Label;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;

public abstract class AbstractPropertyComponentFactory<T> implements PropertyComponentFactory<T> 
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final Class<T> beanType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractPropertyComponentFactory(Class<T> beanType)
    {
        this.beanType = beanType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public Label createPropertyLabel(String id, String propertyName)
    {
        WicketopiaPlugin plugin = WicketopiaPlugin.get();
        PropertyMetaData propertyMetaData = plugin.getBeanMetaData(beanType).getPropertyMetaData(propertyName);
        return new PropertyLabel(id, propertyMetaData);
    }
}
