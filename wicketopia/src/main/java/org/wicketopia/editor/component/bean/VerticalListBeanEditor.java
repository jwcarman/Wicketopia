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

package org.wicketopia.editor.component.bean;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
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

    public VerticalListBeanEditor(String id, Class<T> beanType, IModel<T> beanModel, EditorContext editorContext, String... properties)
    {
        super(id, beanType, beanModel, editorContext, properties);
        final RepeatingView view = new RepeatingView("editors");
        for (String propertyName : getPropertyNameList())
        {
            final Component editor = createPropertyEditor("editor", propertyName);
            final Fragment row = new Fragment(view.newChildId(), "row", VerticalListBeanEditor.this)
            {
                @Override
                public boolean isVisible()
                {
                    return editor.isVisible();
                }
            };
            row.add(createPropertyLabel("label", propertyName));

            row.add(editor);
            view.add(row);
        }
        add(view);
    }
}
