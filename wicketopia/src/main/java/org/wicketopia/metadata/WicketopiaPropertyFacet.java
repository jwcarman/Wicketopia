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

package org.wicketopia.metadata;

import org.metastopheles.FacetKey;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.builder.feature.ComponentBuilderFeature;
import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;
import org.wicketopia.util.ContextualBoolean;
import org.wicketopia.util.Displayable;
import org.wicketopia.util.Pluralizer;

import java.io.Serializable;
import java.util.*;

/**
 * Note: this class has a natural ordering that is inconsistent with equals.
 * @since 1.0
 */
public class WicketopiaPropertyFacet implements Serializable, Displayable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final FacetKey<WicketopiaPropertyFacet> FACET_KEY = new FacetKey<WicketopiaPropertyFacet>()
    {
    };

    private String displayNameMessageKey;
    private String displayName;
    private int order = Integer.MAX_VALUE;
    private String editorType;
    private String viewerType;
    private final Set<ComponentBuilderFeature<EditorBuilder>> editorFeatures = new HashSet<ComponentBuilderFeature<EditorBuilder>>();
    private final Set<ComponentBuilderFeature<ViewerBuilder>> viewerFeatures = new HashSet<ComponentBuilderFeature<ViewerBuilder>>();
    private boolean ignored = false;
    private final PropertyMetaData propertyMetaData;
    private ContextualBoolean viewable = new ContextualBoolean(true);
    private ContextualBoolean editable = new ContextualBoolean(true);
    private ContextualBoolean required = new ContextualBoolean(false);

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static int compare(WicketopiaPropertyFacet leftFacet, WicketopiaPropertyFacet rightFacet)
    {
        return Integer.valueOf(leftFacet.getOrder()).compareTo(rightFacet.getOrder());
    }

    public static WicketopiaPropertyFacet get(PropertyMetaData propertyMetaData)
    {
        synchronized (propertyMetaData)
        {
            WicketopiaPropertyFacet meta = propertyMetaData.getFacet(FACET_KEY);
            if (meta == null)
            {
                meta = new WicketopiaPropertyFacet(propertyMetaData);
                propertyMetaData.setFacet(FACET_KEY, meta);
            }
            return meta;
        }
    }

    public static void sort(List<PropertyMetaData> propertyMetaDataList)
    {
        Collections.sort(propertyMetaDataList, new OrderComparator());
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    WicketopiaPropertyFacet(PropertyMetaData propertyMetaData)
    {
        this.propertyMetaData = propertyMetaData;
        this.displayNameMessageKey = calculateDisplayNameMessageKey(propertyMetaData);
        this.displayName = calculateDefaultDisplayName(propertyMetaData);
    }

    private static String calculateDisplayNameMessageKey(PropertyMetaData propertyMetaData)
    {
        return propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + "." + propertyMetaData.getPropertyDescriptor().getName();
    }

    private static String calculateDefaultDisplayName(PropertyMetaData propertyMetaData)
    {
        final String name = propertyMetaData.getPropertyDescriptor().getName();
        return Pluralizer.splitIntoWords(name);
    }

//----------------------------------------------------------------------------------------------------------------------
// Displayable Implementation
//----------------------------------------------------------------------------------------------------------------------

    public String getDisplayName()
    {
        return displayName;
    }

    public String getDisplayNameMessageKey()
    {
        return displayNameMessageKey;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public void setDisplayNameMessageKey(String displayNameMessageKey)
    {
        this.displayNameMessageKey = displayNameMessageKey;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public Set<ComponentBuilderFeature<EditorBuilder>> getEditorFeatures()
    {
        return editorFeatures;
    }

    public String getEditorType()
    {
        return editorType;
    }

    public void setEditorType(String editorType)
    {
        this.editorType = editorType;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public PropertyMetaData getPropertyMetaData()
    {
        return propertyMetaData;
    }

    public Set<ComponentBuilderFeature<ViewerBuilder>> getViewerFeatures()
    {
        return viewerFeatures;
    }

    public String getViewerType()
    {
        return viewerType;
    }

    public void setViewerType(String viewerType)
    {
        this.viewerType = viewerType;
    }

    public boolean isIgnored()
    {
        return ignored;
    }

    public void setIgnored(boolean ignored)
    {
        this.ignored = ignored;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void addEditorFeature(ComponentBuilderFeature<EditorBuilder> feature)
    {
        editorFeatures.add(feature);
    }

    public void addViewerFeature(ComponentBuilderFeature<ViewerBuilder> feature)
    {
        viewerFeatures.add(feature);
    }

    public void decorate(ViewerBuilder builder, Context context)
    {
        applyFeatures(viewerFeatures, builder, context);
        builder.visible(isVisible(context));
    }

    private <B extends ComponentBuilder> void applyFeatures(Set<? extends ComponentBuilderFeature<B>> features, B builder, Context context)
    {
        for (ComponentBuilderFeature<B> feature : features)
        {
            if(feature.isActiveFor(context))
            {
                feature.activate(builder);
            }
        }
    }

    public void decorate(EditorBuilder builder, Context context)
    {
        applyFeatures(editorFeatures, builder, context);
        builder.visible(isVisible(context));
        builder.required(isRequired(context));
        builder.enabled(isEnabled(context));
    }

    public boolean isEnabled(Context context)
    {
        return editable.getValue(context);
    }
    
    public boolean isRequired(Context context)
    {
        return propertyMetaData.getPropertyDescriptor().getPropertyType().isPrimitive() || required.getValue(context);
    }
    
    public boolean isVisible(Context context)
    {
        return viewable.getValue(context);
    }
    
    public void setEnabled(ContextPredicate predicate, boolean value)
    {
        this.editable.setValue(predicate, value);
    }

    public void setRequired(ContextPredicate predicate, boolean value)
    {
        this.required.setValue(predicate, value);
    }

    public void setVisible(ContextPredicate predicate, boolean value)
    {
        this.viewable.setValue(predicate, value);
    }

    private Object writeReplace()
    {
        return new SerializedForm(getPropertyMetaData());
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class OrderComparator implements Comparator<PropertyMetaData>, Serializable
    {
        @Override
        public int compare(PropertyMetaData left, PropertyMetaData right)
        {
            WicketopiaPropertyFacet leftFacet = WicketopiaPropertyFacet.get(left);
            WicketopiaPropertyFacet rightFacet = WicketopiaPropertyFacet.get(right);
            return WicketopiaPropertyFacet.compare(leftFacet, rightFacet);
        }
    }

    private static final class SerializedForm implements Serializable
    {
        private final PropertyMetaData propertyMetaData;

        private SerializedForm(PropertyMetaData propertyMetaData)
        {
            this.propertyMetaData = propertyMetaData;
        }

        private Object readResolve()
        {
            return WicketopiaPropertyFacet.get(propertyMetaData);
        }
    }
}
