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

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.editor.context.EditorContext;

/**
 * @since 1.0
 */
public class VerticalListBeanEditor<T> extends AbstractBeanEditor<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public VerticalListBeanEditor(String id, Class<T> beanType, IModel<T> beanModel, EditorContext editorContext, String... skippedProperties)
    {
        super(id, beanType, beanModel, editorContext, skippedProperties);
        add(new ListView<PropertyMetaData>("editors", createPropertyMetaDataListModel())
        {
            @Override
            protected void populateItem(ListItem<PropertyMetaData> listItem)
            {
                listItem.add(createPropertyLabel("label", listItem.getModelObject()));
                listItem.add(createPropertyEditor("editor", listItem.getModelObject()));
            }
        });
    }
}
