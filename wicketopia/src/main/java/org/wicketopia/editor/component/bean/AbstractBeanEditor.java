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

package org.wicketopia.editor.component.bean;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.metadata.WicketopiaFacet;

import java.util.*;

/**
 * @since 1.0
 */
public class AbstractBeanEditor<T> extends Panel
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final EditorContext editorContext;
    private final Class<T> beanType;
    private final Set<String> skippedProperties;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractBeanEditor(String id, Class<T> beanType, IModel<T> beanModel, EditorContext editorContext, String... skippedProperties)
    {
        super(id, beanModel);
        this.beanType = beanType;
        this.editorContext = editorContext;
        this.skippedProperties = new TreeSet<String>(Arrays.asList(skippedProperties));
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    protected List<PropertyMetaData> getPropertyMetaDataList()
    {
        List<PropertyMetaData> propertyMetaDataList = new LinkedList<PropertyMetaData>();
        final BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetaData(beanType);
        Set<String> propertyNames = beanMetaData.getPropertyNames();
        propertyNames.removeAll(skippedProperties );
        for (String propertyName : propertyNames)
        {
            propertyMetaDataList.add(beanMetaData.getPropertyMetaData(propertyName));
        }
        WicketopiaFacet.sort(propertyMetaDataList);
        return propertyMetaDataList;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Component createPropertyEditor(String componentId, String propertyName)
    {
        return createPropertyEditor(componentId, getPropertyMetaData(propertyName));
    }

    protected Component createPropertyEditor(String componentId, PropertyMetaData propertyMetaData)
    {
        return WicketopiaPlugin.get().getPropertyEditorFactory().createPropertyEditor(componentId, propertyMetaData, createPropertyModel(propertyMetaData), editorContext);
    }

    protected Label createPropertyLabel(String componentId, String propertyName)
    {
        return createPropertyLabel(componentId, getPropertyMetaData(propertyName));
    }

    protected Label createPropertyLabel(String componentId, PropertyMetaData propertyMetaData)
    {
        return new PropertyLabel(componentId, propertyMetaData);
    }
    
    protected IModel<List<PropertyMetaData>> createPropertyMetaDataListModel()
    {
        return new LoadableDetachableModel<List<PropertyMetaData>>()
        {
            @Override
            protected List<PropertyMetaData> load()
            {
                return getPropertyMetaDataList();
            }
        };
    }

    protected <P> IModel<P> createPropertyModel(PropertyMetaData propertyMetaData)
    {
        return new PropertyModel<P>(getDefaultModel(), propertyMetaData.getPropertyDescriptor().getName());
    }

    protected PropertyMetaData getPropertyMetaData(String propertyName)
    {
        final BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetaData(beanType);
        return beanMetaData.getPropertyMetaData(propertyName);
    }
}
