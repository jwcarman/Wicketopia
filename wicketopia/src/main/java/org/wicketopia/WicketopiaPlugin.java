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

package org.wicketopia;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.protocol.http.WebApplication;
import org.metastopheles.BeanMetaDataFactory;
import org.metastopheles.MetaDataDecorator;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.AnnotationBeanMetaDataFactory;
import org.wicketopia.editor.EditorTypeMapping;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.editor.def.DefaultEditorTypeMapping;
import org.wicketopia.editor.def.DefaultPropertyEditorFactory;
import org.wicketopia.metadata.WicketopiaFacet;

public class WicketopiaPlugin
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static MetaDataKey<WicketopiaPlugin> META_KEY = new WicketopiaPluginKey();

    private BeanMetaDataFactory beanMetadataFactory = new AnnotationBeanMetaDataFactory();
    private PropertyEditorFactory propertyEditorFactory = new DefaultPropertyEditorFactory();
    private EditorTypeMapping editorTypeMapping = new DefaultEditorTypeMapping();

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static WicketopiaPlugin get()
    {
        return WebApplication.get().getMetaData(META_KEY);
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public BeanMetaDataFactory getBeanMetadataFactory()
    {
        return beanMetadataFactory;
    }

    public void setBeanMetadataFactory(BeanMetaDataFactory beanMetadataFactory)
    {
        this.beanMetadataFactory = beanMetadataFactory;
    }

    public EditorTypeMapping getEditorTypeMapping()
    {
        return editorTypeMapping;
    }

    public void setEditorTypeMapping(EditorTypeMapping editorTypeMapping)
    {
        this.editorTypeMapping = editorTypeMapping;
    }

    public PropertyEditorFactory getPropertyEditorFactory()
    {
        return propertyEditorFactory;
    }

    public void setPropertyEditorFactory(PropertyEditorFactory propertyEditorFactory)
    {
        this.propertyEditorFactory = propertyEditorFactory;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void install(WebApplication webApplication)
    {
        beanMetadataFactory.getPropertyMetaDataDecorators().add(new MetaDataDecorator<PropertyMetaData>()
        {
            public void decorate(PropertyMetaData propertyMetaData)
            {
                WicketopiaFacet.get(propertyMetaData).setEditorType(editorTypeMapping.getEditorType(propertyMetaData));
            }
        });
        webApplication.setMetaData(META_KEY, this);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class WicketopiaPluginKey extends MetaDataKey<WicketopiaPlugin>
    {
    }
}
