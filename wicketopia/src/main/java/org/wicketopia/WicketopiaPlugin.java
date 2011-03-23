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
import org.metastopheles.*;
import org.metastopheles.annotation.AnnotationBeanMetaDataFactory;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketopia.editor.PropertyEditorTypeMapping;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.editor.def.DefaultPropertyEditorTypeMapping;
import org.wicketopia.editor.def.DefaultPropertyEditorFactory;
import org.wicketopia.metadata.WicketopiaFacet;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WicketopiaPlugin
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static MetaDataKey<WicketopiaPlugin> META_KEY = new WicketopiaPluginKey();

    private BeanMetaDataFactory beanMetaDataFactory;
    private PropertyEditorFactory propertyEditorFactory = new DefaultPropertyEditorFactory();
    private PropertyEditorTypeMapping propertyEditorTypeMapping = new DefaultPropertyEditorTypeMapping();

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static WicketopiaPlugin get()
    {
        return WebApplication.get().getMetaData(META_KEY);
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public WicketopiaPlugin(WebApplication webApplication)
    {
        this(webApplication, new AnnotationBeanMetaDataFactory(findClasspathUrls(webApplication)));
    }

    private static URL[] findClasspathUrls(WebApplication webApplication)
    {
        final Set<URL> urls = new HashSet<URL>();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if(cl instanceof URLClassLoader)
        {
            urls.addAll(Arrays.asList(((URLClassLoader) cl).getURLs()));
        }
        urls.add(WarUrlFinder.findWebInfClassesPath(webApplication.getServletContext()));
        urls.addAll(Arrays.asList(WarUrlFinder.findWebInfLibClasspaths(webApplication.getServletContext())));
        urls.addAll(Arrays.asList(ClasspathUrlFinder.findClassPaths()));
        urls.remove(null);
        return urls.toArray(new URL[urls.size()]);
    }

    public WicketopiaPlugin(WebApplication webApplication, BeanMetaDataFactory beanMetaDataFactory)
    {
        this.beanMetaDataFactory = beanMetaDataFactory;
        beanMetaDataFactory.getPropertyMetaDataDecorators().add(new MetaDataDecorator<PropertyMetaData>()
        {
            public void decorate(PropertyMetaData propertyMetaData)
            {
                WicketopiaFacet.get(propertyMetaData).setEditorType(propertyEditorTypeMapping.getEditorType(propertyMetaData));
            }
        });
        webApplication.setMetaData(META_KEY, this);
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public PropertyEditorFactory getPropertyEditorFactory()
    {
        return propertyEditorFactory;
    }

    public void setPropertyEditorFactory(PropertyEditorFactory propertyEditorFactory)
    {
        this.propertyEditorFactory = propertyEditorFactory;
    }

    public PropertyEditorTypeMapping getPropertyEditorTypeMapping()
    {
        return propertyEditorTypeMapping;
    }

    public void setPropertyEditorTypeMapping(PropertyEditorTypeMapping propertyEditorTypeMapping)
    {
        this.propertyEditorTypeMapping = propertyEditorTypeMapping;
    }

    /*public BeanMetaDataFactory getBeanMetadataFactory()
    {
        return beanMetadataFactory;
    }*/

    public void setBeanMetaDataFactory(BeanMetaDataFactory beanMetaDataFactory)
    {
        this.beanMetaDataFactory = beanMetaDataFactory;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void addBeanMetaDataDecorator(MetaDataDecorator<BeanMetaData> decorator)
    {
        beanMetaDataFactory.getBeanMetaDataDecorators().add(decorator);
    }

    public void addMethodMetaDataDecorator(MetaDataDecorator<MethodMetaData> decorator)
    {
        beanMetaDataFactory.getMethodMetaDataDecorators().add(decorator);
    }

    public void addPropertyMetaDataDecorator(MetaDataDecorator<PropertyMetaData> decorator)
    {
        beanMetaDataFactory.getPropertyMetaDataDecorators().add(decorator);
    }

    public BeanMetaData getBeanMetaData(Class<?> beanClass)
    {
        if(WebApplication.get().getConfigurationType().equals(WebApplication.DEVELOPMENT))
        {
            beanMetaDataFactory.clear();
        }
        return beanMetaDataFactory.getBeanMetaData(beanClass);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class WicketopiaPluginKey extends MetaDataKey<WicketopiaPlugin>
    {
    }
}
