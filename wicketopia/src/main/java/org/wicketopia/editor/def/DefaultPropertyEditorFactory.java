/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.provider.EnumChoicePropertyEditorProvider;
import org.wicketopia.editor.provider.TextAreaPropertyEditorProvider;
import org.wicketopia.editor.provider.TextFieldPropertyEditorProvider;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 1.0
 */
public class DefaultPropertyEditorFactory implements PropertyEditorFactory
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private Map<String, PropertyEditorProvider> providerMap =
            new HashMap<String, PropertyEditorProvider>();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public DefaultPropertyEditorFactory()
    {
        final TextFieldPropertyEditorProvider textFieldProviderProperty = new TextFieldPropertyEditorProvider();
        setEditorProviderOverride("string", textFieldProviderProperty);
        setEditorProviderOverride("short", textFieldProviderProperty);
        setEditorProviderOverride("integer", textFieldProviderProperty);
        setEditorProviderOverride("double", textFieldProviderProperty);
        setEditorProviderOverride("long", textFieldProviderProperty);
        setEditorProviderOverride("float", textFieldProviderProperty);
        setEditorProviderOverride("date", textFieldProviderProperty);
        
        setEditorProviderOverride("long-string", new TextAreaPropertyEditorProvider());
        setEditorProviderOverride("enum", new EnumChoicePropertyEditorProvider());
    }

//**********************************************************************************************************************
// PropertyEditorFactory Implementation
//**********************************************************************************************************************

    public Component createPropertyEditor(String id, PropertyMetaData propertyMetadata, IModel<?> propertyModel, EditorContext context)
    {
        WicketopiaPropertyMetaData wicketopiaPropertyMetaData = WicketopiaPropertyMetaData.get(propertyMetadata);
        String editorType = wicketopiaPropertyMetaData.getEditorType();
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
        for (PropertyEditorFacet facet : wicketopiaPropertyMetaData.getFacets())
        {
            facet.apply(builder, context);
        }
        return builder.getEditorComponent();
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public void setEditorProviderOverride(String editorType, PropertyEditorProvider provider)
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
