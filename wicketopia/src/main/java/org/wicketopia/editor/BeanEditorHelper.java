/*
 * Copyright (c) 2010 Carman Consulting, Inc.
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

package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.metadata.WicketopiaFacet;

import java.io.Serializable;
import java.util.*;

public class BeanEditorHelper<T> implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Class<T> beanClass;
    private final IModel<T> beanModel;
    private final EditorContext editorContext;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanEditorHelper(String editType, Class<T> beanClass, IModel<T> beanModel)
    {
        this.editorContext = new EditorContext(editType);
        this.beanClass = beanClass;
        this.beanModel = beanModel;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public EditorContext getEditorContext()
    {
        return editorContext;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public ListView<String> createEditorsView(String componentId, String... skippedProperties)
    {
        final ListView<String> listView = new EditorListView(componentId, skippedProperties);
        listView.setReuseItems(true);
        return listView;
    }

    public Component createPropertyEditor(String componentId, String propertyName)
    {
        PropertyMetaData propertyMetaData = getPropertyMetaData(propertyName);
        return WicketopiaPlugin.get().getPropertyEditorFactory().createPropertyEditor(componentId, propertyMetaData, new PropertyModel(beanModel, propertyName), editorContext);
    }

    private PropertyMetaData getPropertyMetaData(String propertyName)
    {
        BeanMetaData beanMetaData = getBeanMetaData();
        return beanMetaData.getPropertyMetaData(propertyName);
    }

    private BeanMetaData getBeanMetaData()
    {
        return WicketopiaPlugin.get().getBeanMetadataFactory().getBeanMetaData(beanClass);
    }

    public Label createPropertyLabel(String componentId, String propertyName)
    {
        return new PropertyLabel(componentId, getPropertyMetaData(propertyName));
    }

    public List<PropertyMetaData> getPropertyMetaData(String... skippedProperties)
    {
        final BeanMetaData beanMetaData = getBeanMetaData();
        Set<String> propertyNames = beanMetaData.getPropertyNames();
        propertyNames.removeAll(Arrays.asList(skippedProperties));
        final List<PropertyMetaData> propertyMetaDatas = new ArrayList<PropertyMetaData>(propertyNames.size());
        for (String propertyName : propertyNames)
        {
            propertyMetaDatas.add(beanMetaData.getPropertyMetaData(propertyName));
        }
        Collections.sort(propertyMetaDatas, new Comparator<PropertyMetaData>()
        {
            public int compare(PropertyMetaData o1, PropertyMetaData o2)
            {
                return WicketopiaFacet.get(o1).compareTo(WicketopiaFacet.get(o2));
            }
        });
        return propertyMetaDatas;
    }

    public List<String> getPropertyNames(String... skippedProperties)
    {
        final List<PropertyMetaData> propertyMetaDatas = getPropertyMetaData(skippedProperties);
        List<String> propertyNames = new ArrayList<String>(propertyMetaDatas.size());
        for (PropertyMetaData propertyMetaData : propertyMetaDatas)
        {
            propertyNames.add(propertyMetaData.getPropertyDescriptor().getName());
        }
        return propertyNames;
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class EditorListView extends ListView<String>
    {
        public EditorListView(String componentId, String... skippedProperties)
        {
            super(componentId, BeanEditorHelper.this.getPropertyNames(skippedProperties));
        }

        @Override
        protected void populateItem(ListItem<String> item)
        {
            final String propertyName = item.getModelObject();
            item.add(createPropertyLabel("label", propertyName));
            item.add(createPropertyEditor("editor", propertyName));
        }
    }
}
