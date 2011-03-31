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

package org.wicketopia.editor.component.property;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;

/**
 * @since 1.0
 */
public class TextAreaPropertyEditor<T> extends AbstractTextComponentPropertyEditor
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------
    public static final String TYPE_NAME = "text-area";
    private static final PropertyEditorProvider provider = new Provider();

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static PropertyEditorProvider getProvider()
    {
        return provider;
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public TextAreaPropertyEditor(String id, PropertyMetaData propertyMetaData, TextArea formComponent)
    {
        super(id, propertyMetaData, formComponent);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class Provider implements PropertyEditorProvider
    {
        @SuppressWarnings("unchecked")
        public EditorBuilder createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
        {
            return new TextAreaPropertyEditor(componentId, propertyMetadata, new TextArea("editor", propertyModel));
        }
    }
}
