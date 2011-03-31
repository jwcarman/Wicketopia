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

package org.wicketopia.metadata;

import org.metastopheles.FacetKey;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.builder.feature.ComponentBuilderFeature;
import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;
import org.wicketopia.util.Displayable;
import org.wicketopia.util.Pluralizer;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @since 1.0
 */
public class WicketopiaPropertyFacet implements Comparable, Serializable, Displayable
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
        Collections.sort(propertyMetaDataList, new Comparator<PropertyMetaData>()
        {
            @Override
            public int compare(PropertyMetaData o1, PropertyMetaData o2)
            {
                return WicketopiaPropertyFacet.get(o1).compareTo(WicketopiaPropertyFacet.get(o2));
            }
        });
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    WicketopiaPropertyFacet(PropertyMetaData propertyMetaData)
    {
        this.propertyMetaData = propertyMetaData;
        this.displayNameMessageKey = propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + "." + propertyMetaData.getPropertyDescriptor().getName();
        this.displayName = calculateDefaultLabelText(propertyMetaData.getPropertyDescriptor());
    }

    private String calculateDefaultLabelText(PropertyDescriptor propertyDescriptor)
    {
        final String name = propertyDescriptor.getName();
        return Pluralizer.splitIntoWords(name);
    }


//----------------------------------------------------------------------------------------------------------------------
// Comparable Implementation
//----------------------------------------------------------------------------------------------------------------------

    public int compareTo(Object o)
    {
        if (o instanceof WicketopiaPropertyFacet)
        {
            WicketopiaPropertyFacet other = (WicketopiaPropertyFacet) o;
            return new Integer(getOrder()).compareTo(other.getOrder());
        }
        return 1;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

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

    public String getDisplayNameMessageKey()
    {
        return displayNameMessageKey;
    }

    public void setDisplayNameMessageKey(String displayNameMessageKey)
    {
        this.displayNameMessageKey = displayNameMessageKey;
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
//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class ContextualBoolean
    {
        private final boolean defaultValue;
        private final List<ContextualCondition> conditions = new LinkedList<ContextualCondition>();

        private ContextualBoolean(boolean defaultValue)
        {
            this.defaultValue = defaultValue;
        }
        
        public void setValue(ContextPredicate predicate, boolean value)
        {
            conditions.add(new ContextualCondition(predicate, value));
        }
        
        public boolean getValue(Context context)
        {
            if(conditions.isEmpty())
            {
                return defaultValue;
            }
            boolean aggregate = true;
            for (ContextualCondition condition : conditions)
            {
                if(condition.predicate.evaluate(context))
                {
                    aggregate = aggregate && condition.value;
                }
                else
                {
                    aggregate = aggregate && !condition.value;
                }
            }
            return aggregate;
        }
    }

    private class ContextualCondition
    {
        private final ContextPredicate predicate;
        private final boolean value;

        private ContextualCondition(ContextPredicate predicate, boolean value)
        {
            this.predicate = predicate;
            this.value = value;
        }
    }

    private static class SerializedForm implements Serializable
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

    public void decorate(ViewerBuilder builder, Context context)
    {
        applyFeatures(viewerFeatures, builder, context);
        builder.visible(isVisible(context));
    }

    public void decorate(EditorBuilder builder, Context context)
    {
        applyFeatures(editorFeatures, builder, context);
        builder.visible(isVisible(context));
        builder.required(isRequired(context));
        builder.enabled(isEnabled(context));
    }
}
