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

package org.wicketopia.editor.component.property;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;

public class TextFieldPropertyEditor extends AbstractTextComponentPropertyEditor
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String TYPE_NAME = "text-field";
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

    public TextFieldPropertyEditor(String id, PropertyMetaData propertyMetaData, TextField<?> textField)
    {
        super(id, propertyMetaData, textField);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class Provider implements PropertyEditorProvider
    {
        public PropertyEditor createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
        {
            return new TextFieldPropertyEditor(componentId, propertyMetadata, new TextField("editor", propertyModel));
        }
    }
}
