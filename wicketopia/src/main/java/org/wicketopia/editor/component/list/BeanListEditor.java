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

package org.wicketopia.editor.component.list;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListItemModel;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.factory.BeanEditorComponentFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @since 1.0
 * @author James Carman
 */
public class BeanListEditor<T> extends Panel
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final BeanListView listView;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanListEditor(String id,
                          Class<T> beanType,
                          IModel<? extends List<? extends T>> model,
                          EditorContext editorContext,
                          String... properties)
    {
        super(id);
        BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetaData(beanType);
        final RepeatingView headers = new RepeatingView("headers");
        for (String property : properties)
        {
            PropertyMetaData propertyMetaData = beanMetaData.getPropertyMetaData(property);
            if(propertyMetaData == null)
            {
                throw new WicketRuntimeException("Property \"" + property + "\" not found for bean type " + beanType.getName() + ".");
            }
            headers.add(new PropertyLabel(headers.newChildId(), propertyMetaData));
        }
        add(headers);
        listView = new BeanListView("rows", model, beanType, editorContext, properties);
        add(listView);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected IModel<T> getListItemModel(IModel<? extends List<? extends T>> model, int index)
    {
        return new ListItemModel<T>(listView, index);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class BeanListView extends ListView<T>
    {
        private final BeanEditorComponentFactory<T> editorComponentFactory;
        private final List<String> propertyNames;
        private final EditorContext editorContext;

        private BeanListView(String id, IModel<? extends List<? extends T>> model, Class<T> beanType, EditorContext editorContext, String... properties)
        {
            super(id, model);
            editorComponentFactory = new BeanEditorComponentFactory<T>(beanType);
            this.editorContext = editorContext;
            propertyNames = new ArrayList<String>(Arrays.asList(properties));
            setReuseItems(true);
        }

        @Override
        protected void populateItem(ListItem<T> rowItem)
        {
            final IModel<T> beanModel = rowItem.getModel();
            ListView<String> cells = new ListView<String>("cells", propertyNames)
            {
                @Override
                protected void populateItem(ListItem<String> cellItem)
                {
                    cellItem.add(editorComponentFactory.createPropertyEditor("editor", beanModel, cellItem.getModelObject(), editorContext));
                }
            };
            cells.setReuseItems(true);
            rowItem.add(cells);

        }

        @Override
        protected IModel<T> getListItemModel(IModel<? extends List<T>> model, int index)
        {
            return BeanListEditor.this.getListItemModel(model, index);
        }
    }
}
