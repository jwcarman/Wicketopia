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

package org.wicketopia.editor.provider;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.component.choice.EnumDropDownChoice;
import org.wicketopia.context.Context;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.DropDownChoicePropertyEditor;

public class EnumDropDownChoicePropertyEditorProvider implements PropertyEditorProvider {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String TYPE_NAME = "enum-ddc";

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public EditorBuilder createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel, Context context) {
        final Class<?> propertyType = propertyMetadata.getPropertyDescriptor().getPropertyType();
        if (!propertyType.isEnum()) {
            throw new IllegalArgumentException("Property '" + propertyMetadata.getPropertyDescriptor().getName() + "' is not an enum.");
        }
        return new DropDownChoicePropertyEditor(componentId, propertyMetadata, new EnumDropDownChoice(DropDownChoicePropertyEditor.COMPONENT_ID, propertyModel, propertyType));
    }
}
