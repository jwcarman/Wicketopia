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

package org.wicketopia;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.metastopheles.BeanMetaData;
import org.metastopheles.BeanMetaDataFactory;
import org.metastopheles.MetaDataDecorator;
import org.metastopheles.MethodMetaData;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.AnnotationBeanMetaDataFactory;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.context.Context;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.CheckBoxPropertyEditor;
import org.wicketopia.editor.component.property.EnumDropDownChoicePropertyEditor;
import org.wicketopia.editor.component.property.PasswordFieldPropertyEditor;
import org.wicketopia.editor.component.property.TextAreaPropertyEditor;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.factory.PropertyEditorComponentFactory;
import org.wicketopia.factory.PropertyViewerComponentFactory;
import org.wicketopia.mapping.TypeMapping;
import org.wicketopia.mapping.editor.DefaultEditorTypeMapping;
import org.wicketopia.mapping.viewer.DefaultViewerTypeMapping;
import org.wicketopia.metadata.WicketopiaPropertyFacet;
import org.wicketopia.model.column.BeanPropertyColumn;
import org.wicketopia.util.Pluralizer;
import org.wicketopia.util.ServiceLocator;
import org.wicketopia.viewer.PropertyViewerProvider;
import org.wicketopia.viewer.component.LabelPropertyViewer;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Wicketopia
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger logger = LoggerFactory.getLogger(Wicketopia.class);

    private static final MetaDataKey<Wicketopia> META_KEY = new WicketopiaPluginKey();

    private BeanMetaDataFactory beanMetaDataFactory = new AnnotationBeanMetaDataFactory(findClasspathUrls());
    private TypeMapping editorTypeMapping = new DefaultEditorTypeMapping();
    private TypeMapping viewerTypeMapping = new DefaultViewerTypeMapping();
    private final Map<String, PropertyEditorProvider> editorProviders = new HashMap<String, PropertyEditorProvider>();
    private final Map<String, PropertyViewerProvider> viewerProviders = new HashMap<String, PropertyViewerProvider>();
    private final List<WicketopiaPlugin> plugins;
    private WebApplication application;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    private static URL[] findClasspathUrls()
    {
        final Set<URL> urls = new HashSet<URL>();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl instanceof URLClassLoader)
        {
            urls.addAll(Arrays.asList(((URLClassLoader) cl).getURLs()));
        }
        WebApplication webApplication = WebApplication.get();
        if (webApplication != null)
        {
            urls.add(WarUrlFinder.findWebInfClassesPath(webApplication.getServletContext()));
            urls.addAll(Arrays.asList(WarUrlFinder.findWebInfLibClasspaths(webApplication.getServletContext())));
        }
        urls.addAll(Arrays.asList(ClasspathUrlFinder.findClassPaths()));
        urls.remove(null);
        return urls.toArray(new URL[urls.size()]);
    }

    public static Wicketopia get()
    {
        return WebApplication.get().getMetaData(META_KEY);
    }

    /**
     * Installs Wicketopia into the currently-running web application using all default settings.
     */
    public static void install()
    {
        new Wicketopia().install(WebApplication.get());
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public Wicketopia()
    {
        this(findDefaultPlugins());
    }
    
    private static List<WicketopiaPlugin> findDefaultPlugins()
    {
        final List<WicketopiaPlugin> plugins = new LinkedList<WicketopiaPlugin>();
        for (WicketopiaPlugin wicketopiaPlugin : ServiceLocator.findAll(WicketopiaPlugin.class))
        {
            plugins.add(wicketopiaPlugin);
        }
        return plugins;
    }

    public Wicketopia(List<WicketopiaPlugin> plugins)
    {
        this.plugins = plugins;
    }

    public Wicketopia(WicketopiaPlugin... plugins)
    {
        this(Arrays.asList(plugins));
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public WebApplication getApplication()
    {
        return application;
    }

    public TypeMapping getEditorTypeMapping()
    {
        return editorTypeMapping;
    }

    public void setEditorTypeMapping(TypeMapping editorTypeMapping)
    {
        this.editorTypeMapping = editorTypeMapping;
    }

    public TypeMapping getViewerTypeMapping()
    {
        return viewerTypeMapping;
    }

    public void setViewerTypeMapping(TypeMapping viewerTypeMapping)
    {
        this.viewerTypeMapping = viewerTypeMapping;
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

    public void addEditorTypeOverride(Class<?> propertyType, String typeName)
    {
        editorTypeMapping.addTypeOverride(propertyType, typeName);
    }

    public void addMethodMetaDataDecorator(MetaDataDecorator<MethodMetaData> decorator)
    {
        beanMetaDataFactory.getMethodMetaDataDecorators().add(decorator);
    }

    public void addPlugin(WicketopiaPlugin plugin)
    {
        plugins.add(plugin);
    }

    public void addPropertyEditorProvider(String typeName, PropertyEditorProvider provider)
    {
        editorProviders.put(typeName, provider);
    }

    public void addPropertyMetaDataDecorator(MetaDataDecorator<PropertyMetaData> decorator)
    {
        beanMetaDataFactory.getPropertyMetaDataDecorators().add(decorator);
    }

    public void addPropertyViewerProvider(String typeName, PropertyViewerProvider provider)
    {
        viewerProviders.put(typeName, provider);
    }

    public void addViewerTypeOverride(Class<?> propertyType, String typeName)
    {
        viewerTypeMapping.addTypeOverride(propertyType, typeName);
    }

    public String calculateDefaultDisplayName(BeanMetaData beanMetaData)
    {
        return Pluralizer.splitIntoWords(beanMetaData.getBeanDescriptor().getBeanClass().getSimpleName());
    }

    public String calculateDefaultDisplayName(PropertyMetaData propertyMetaData)
    {
        final String name = propertyMetaData.getPropertyDescriptor().getName();
        return Pluralizer.splitIntoWords(name);
    }

    public String calculateDisplayNameMessageKey(BeanMetaData beanMetaData)
    {
        return beanMetaData.getBeanDescriptor().getBeanClass().getName();
    }

    public String calculateDisplayNameMessageKey(PropertyMetaData propertyMetaData)
    {
        return propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + "." + propertyMetaData.getPropertyDescriptor().getName();
    }

    public <T> List<IColumn<T>> createColumns(Class<T> beanType, PropertyComponentFactory<T> factory, Context context, String... properties)
    {
        final List<String> visible = getVisibleProperties(beanType, context, properties);
        final List<IColumn<T>> columns = new ArrayList<IColumn<T>>(visible.size());
        for (String propertyName : visible)
        {
            columns.add(new BeanPropertyColumn<T>(factory, propertyName, context));
        }
        return columns;
    }

    public <T> PropertyComponentFactory<T> createEditorFactory(Class<T> beanType)
    {
        return new PropertyEditorComponentFactory<T>(beanType);
    }

    public Component createPropertyEditor(String id, PropertyMetaData propertyMetadata, IModel<?> propertyModel, Context context)
    {
        final WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetadata);
        EditorBuilder builder = getEditorProvider(propertyMetadata).createPropertyEditor(id, propertyMetadata, propertyModel);
        facet.decorate(builder, context);
        return builder.build();
    }

    public Component createPropertyViewer(String id, PropertyMetaData propertyMetaData, IModel<?> propertyModel, Context context)
    {
        final WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
        ViewerBuilder builder = getViewerProvider(propertyMetaData).createPropertyViewer(id, propertyMetaData, propertyModel);
        facet.decorate(builder, context);
        return builder.build();
    }

    public <T> PropertyComponentFactory<T> createViewerFactory(Class<T> beanType)
    {
        return new PropertyViewerComponentFactory<T>(beanType);
    }

    public BeanMetaData getBeanMetaData(Class<?> beanClass)
    {
        if (WebApplication.get().getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT))
        {
            beanMetaDataFactory.clear();
        }
        return beanMetaDataFactory.getBeanMetaData(beanClass);
    }

    public PropertyEditorProvider getEditorProvider(PropertyMetaData propertyMetaData)
    {
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
        String editorType = facet.getEditorType();
        if (editorType == null)
        {
            editorType = editorTypeMapping.getTypeName(propertyMetaData);
            facet.setEditorType(editorType);
        }
        if (editorType == null)
        {
            throw new WicketRuntimeException("No editor type defined for property " +
                    propertyMetaData.getPropertyDescriptor().getName() + " of class " +
                    propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + ".");
        }
        PropertyEditorProvider provider = editorProviders.get(editorType);
        if (provider == null)
        {
            throw new WicketRuntimeException("No property editor provider registered for editor type \"" + editorType + "\" for property " +
                    propertyMetaData.getPropertyDescriptor().getName() + " of class " +
                    propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + ".");
        }
        return provider;
    }

    /**
     * Retrieves a plugin by type
     *
     * @param pluginType the plugin type
     * @param <P>        the plugin type
     * @return the plugin
     */
    public <P extends WicketopiaPlugin> P getPlugin(Class<P> pluginType)
    {
        for (WicketopiaPlugin plugin : plugins)
        {
            if (pluginType.isInstance(plugin))
            {
                return pluginType.cast(plugin);
            }
        }
        throw new WicketRuntimeException("No plugin of type " + pluginType.getName() + " is installed.");
    }

    public PropertyViewerProvider getViewerProvider(PropertyMetaData propertyMetaData)
    {
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
        String viewerType = facet.getViewerType();
        if (viewerType == null)
        {
            viewerType = viewerTypeMapping.getTypeName(propertyMetaData);
            facet.setViewerType(viewerType);
        }
        if (viewerType == null)
        {
            throw new WicketRuntimeException("No viewer type defined for property " +
                    propertyMetaData.getPropertyDescriptor().getName() + " of class " +
                    propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + ".");
        }
        PropertyViewerProvider provider = viewerProviders.get(viewerType);
        if (provider == null)
        {
            throw new WicketRuntimeException("No property viewer provider registered for viewer type \"" + viewerType + "\" for property " +
                    propertyMetaData.getPropertyDescriptor().getName() + " of class " +
                    propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + ".");
        }
        return viewerProviders.get(viewerType);
    }

    public List<String> getVisibleProperties(Class<?> beanType, Context context, String... properties)
    {
        final List<String> names = new LinkedList<String>();
        final BeanMetaData beanMetaData = getBeanMetaData(beanType);
        if (properties == null || properties.length == 0)
        {
            for (String propertyName : beanMetaData.getPropertyNames())
            {
                WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(beanMetaData.getPropertyMetaData(propertyName));
                if (!facet.isIgnored() && facet.isVisible(context))
                {
                    names.add(propertyName);
                }
            }
            Collections.sort(names, new Comparator<String>()
            {
                @Override
                public int compare(String o1, String o2)
                {
                    WicketopiaPropertyFacet facet1 = WicketopiaPropertyFacet.get(beanMetaData.getPropertyMetaData(o1));
                    WicketopiaPropertyFacet facet2 = WicketopiaPropertyFacet.get(beanMetaData.getPropertyMetaData(o2));
                    return facet1.compareTo(facet2);
                }
            });
        }
        else
        {
            for (String propertyName : properties)
            {
                WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(beanMetaData.getPropertyMetaData(propertyName));
                if (!facet.isIgnored() && facet.isVisible(context))
                {
                    names.add(propertyName);
                }
            }
        }
        return names;
    }

    public void install(WebApplication application)
    {
        this.application = application;
        application.setMetaData(META_KEY, this);
        addDefaultEditorProviders();
        adDefaultViewerProviders();
        for (WicketopiaPlugin plugin : plugins)
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("Initializing {} plugin...", plugin.getClass().getName());
            }
            plugin.initialize(this);
        }
    }

    private void addDefaultEditorProviders()
    {
        addPropertyEditorProvider(TextFieldPropertyEditor.TYPE_NAME, TextFieldPropertyEditor.getProvider());
        addPropertyEditorProvider(TextAreaPropertyEditor.TYPE_NAME, TextAreaPropertyEditor.getProvider());
        addPropertyEditorProvider(EnumDropDownChoicePropertyEditor.TYPE_NAME, EnumDropDownChoicePropertyEditor.getProvider());
        addPropertyEditorProvider(PasswordFieldPropertyEditor.TYPE_NAME, PasswordFieldPropertyEditor.getProvider());
        addPropertyEditorProvider(CheckBoxPropertyEditor.TYPE_NAME, CheckBoxPropertyEditor.getProvider());
    }

    private void adDefaultViewerProviders()
    {
        addPropertyViewerProvider(LabelPropertyViewer.TYPE_NAME, LabelPropertyViewer.getProvider());
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static final class WicketopiaPluginKey extends MetaDataKey<Wicketopia>
    {
    }
}
