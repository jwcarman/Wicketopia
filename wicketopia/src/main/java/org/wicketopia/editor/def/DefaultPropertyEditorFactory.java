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

package org.wicketopia.editor.def;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.editor.component.property.EnumDropDownChoicePropertyEditor;
import org.wicketopia.editor.component.property.TextAreaPropertyEditor;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorDecorator;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.metadata.WicketopiaFacet;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 1.0
 */
public class DefaultPropertyEditorFactory implements PropertyEditorFactory
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private Map<String, PropertyEditorProvider> providerMap =
            new HashMap<String, PropertyEditorProvider>();

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DefaultPropertyEditorFactory()
    {
        registerProvider("string", TextFieldPropertyEditor.getProvider());
        registerProvider("short", TextFieldPropertyEditor.getProvider());
        registerProvider("integer", TextFieldPropertyEditor.getProvider());
        registerProvider("double", TextFieldPropertyEditor.getProvider());
        registerProvider("long", TextFieldPropertyEditor.getProvider());
        registerProvider("float", TextFieldPropertyEditor.getProvider());
        registerProvider("date", TextFieldPropertyEditor.getProvider());
        
        registerProvider("long-string", TextAreaPropertyEditor.getProvider());
        registerProvider("enum", EnumDropDownChoicePropertyEditor.getProvider());
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorFactory Implementation
//----------------------------------------------------------------------------------------------------------------------

    public Component createPropertyEditor(String id, PropertyMetaData propertyMetadata, IModel<?> propertyModel, EditorContext context)
    {
        WicketopiaFacet wicketopiaFacet = WicketopiaFacet.get(propertyMetadata);
        String editorType = wicketopiaFacet.getEditorType();
        if (editorType == null)
        {
            throw new IllegalArgumentException("No editor type defined for property " +
                    propertyMetadata.getPropertyDescriptor().getName() + " of class " +
                    propertyMetadata.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + ".");
        }
        PropertyEditorProvider provider = providerMap.get(editorType);
        if (provider == null)
        {
            throw new IllegalArgumentException(
                    "No property editor builder defined for editor type \"" + editorType + ".\"");
        }
        PropertyEditor builder = provider.createPropertyEditor(id, propertyMetadata, propertyModel);
        for (PropertyEditorDecorator decorator : wicketopiaFacet.getDecorators())
        {
            decorator.apply(builder, context);
        }
        return builder.getEditorComponent();
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void registerProvider(String editorType, PropertyEditorProvider provider)
    {
        providerMap.put(editorType, provider);
    }

    public void setProviderOverrides(Map<String, PropertyEditorProvider> providerOverrides)
    {
        for (String editorType : providerOverrides.keySet())
        {
            providerMap.put(editorType, providerOverrides.get(editorType));
        }
    }
}
