/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.editor.factory;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.editor.context.EditorContext;

import java.io.Serializable;

/**
 * @author James Carman
 */
public class BeanEditorComponentFactory<T> implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Class<T> beanType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanEditorComponentFactory(Class<T> beanType)
    {
        this.beanType = beanType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public Component createPropertyEditor(String id, IModel<T> beanModel, String propertyName, EditorContext context)
    {
        WicketopiaPlugin plugin = WicketopiaPlugin.get();
        PropertyMetaData propertyMetaData = plugin.getBeanMetaData(beanType).getPropertyMetaData(propertyName);
        return plugin.getPropertyEditorFactory().createPropertyEditor(id, propertyMetaData, new PropertyModel(beanModel, propertyName), context);
    }

    public Label createPropertyLabel(String id, String propertyName)
    {
        WicketopiaPlugin plugin = WicketopiaPlugin.get();
        PropertyMetaData propertyMetaData = plugin.getBeanMetaData(beanType).getPropertyMetaData(propertyName);
        return new PropertyLabel(id, propertyMetaData);
    }
}
