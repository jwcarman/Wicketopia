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

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.component.choice.EnumDropDownChoice;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;

/**
 * @since 1.0
 */
public class EnumDropDownChoicePropertyEditor extends AbstractFormComponentPropertyEditor
{
    public static final String TYPE_NAME = "enum-ddc";

//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

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

    public EnumDropDownChoicePropertyEditor(String id, PropertyMetaData propertyMetaData, EnumDropDownChoice<?> formComponent)
    {
        super(id, propertyMetaData, formComponent);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class Provider implements PropertyEditorProvider
    {
        public PropertyEditor createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
        {
            return new EnumDropDownChoicePropertyEditor(componentId, propertyMetadata, new EnumDropDownChoice("ddc", propertyModel, propertyMetadata.getPropertyDescriptor().getPropertyType()));
        }
    }
}
