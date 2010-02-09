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

package org.wicketopia.metadata;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.WicketRuntimeException;
import org.metastopheles.AttributeKey;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.editor.PropertyEditorFacet;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @since 1.0
 */
public class WicketopiaPropertyMetaData implements Comparable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private String labelTextMessageKey;
    private String defaultLabelText;
    private int order = Integer.MAX_VALUE;
    private String editorType;
    private Set<PropertyEditorFacet> facets = new HashSet<PropertyEditorFacet>();

    private final PropertyMetaData propertyMetaData;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    WicketopiaPropertyMetaData(PropertyMetaData propertyMetaData)
    {
        this.propertyMetaData = propertyMetaData;
        this.labelTextMessageKey = propertyMetaData.getBeanMetaData().getBeanDescriptor().getBeanClass().getName() + "." + propertyMetaData.getPropertyDescriptor().getName();
        this.defaultLabelText = calculateDefaultLabelText(propertyMetaData.getPropertyDescriptor());
    }

//**********************************************************************************************************************
// Comparable Implementation
//**********************************************************************************************************************

    public int compareTo(Object o)
    {
        if (o instanceof WicketopiaPropertyMetaData)
        {
            WicketopiaPropertyMetaData other = (WicketopiaPropertyMetaData) o;
            return new Integer(order).compareTo(other.order);
        }
        return 1;
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************


    public PropertyMetaData getPropertyMetaData()
    {
        return propertyMetaData;
    }

    public String getDefaultLabelText()
    {
        return defaultLabelText;
    }

    public void setDefaultLabelText(String defaultLabelText)
    {
        this.defaultLabelText = defaultLabelText;
    }

    public String getEditorType()
    {
        return editorType;
    }

    public void setEditorType(String editorType)
    {
        this.editorType = editorType;
    }

    public Set<PropertyEditorFacet> getFacets()
    {
        return facets;
    }

    public String getLabelTextMessageKey()
    {
        return labelTextMessageKey;
    }

    public void setLabelTextMessageKey(String labelTextMessageKey)
    {
        this.labelTextMessageKey = labelTextMessageKey;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    private String calculateDefaultLabelText(PropertyDescriptor propertyDescriptor)
    {
        String[] words = StringUtils.splitByCharacterTypeCamelCase(propertyDescriptor.getName());
        words[0] = StringUtils.capitalize(words[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            sb.append(word);
            if (i != words.length - 1)
            {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public void addFacet(PropertyEditorFacet facet)
    {
        facets.add(facet);
    }

    Object writeReplace()
    {
        return new SerializedForm(getPropertyMetaData().getBeanMetaData().getBeanDescriptor().getBeanClass().getName(), getPropertyMetaData().getPropertyDescriptor().getName());
    }

//**********************************************************************************************************************
// Inner Classes
//**********************************************************************************************************************

    private static AttributeKey<WicketopiaPropertyMetaData> ATTRIBUTE_KEY = new AttributeKey<WicketopiaPropertyMetaData>()
    {
    };

    public static WicketopiaPropertyMetaData get(PropertyMetaData propertyMetaData)
    {
        synchronized (propertyMetaData)
        {
            WicketopiaPropertyMetaData meta = propertyMetaData.getAttribute(ATTRIBUTE_KEY);
            if (meta == null)
            {
                meta = new WicketopiaPropertyMetaData(propertyMetaData);
                propertyMetaData.setAttribute(ATTRIBUTE_KEY, meta);
            }
            return meta;
        }
    }

    private static class SerializedForm implements Serializable
    {
        private final String className;
        private final String propertyName;

        private SerializedForm(String className, String propertyName)
        {
            this.className = className;
            this.propertyName = propertyName;
        }

        Object readResolve()
        {
            try
            {
                Class beanClass = Class.forName(className);
                BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetadataFactory().getBeanMetaData(beanClass);
                PropertyMetaData propertyMetaData = beanMetaData.getPropertyMetaData(propertyName);
                return WicketopiaPropertyMetaData.get(propertyMetaData);
            }
            catch (ClassNotFoundException e)
            {
                throw new WicketRuntimeException("Unable to find bean class " + className + ".", e);
            }
        }
    }
}
