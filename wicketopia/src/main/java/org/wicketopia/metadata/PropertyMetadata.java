package org.wicketopia.metadata;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.WicketRuntimeException;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.editor.PropertyEditorFacet;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @since 1.0
 */
public class PropertyMetadata implements Serializable, Comparable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final BeanMetadata beanMetadata;
    private final PropertyDescriptor propertyDescriptor;
    private String labelTextMessageKey;
    private String defaultLabelText;
    private int order = Integer.MAX_VALUE;
    private String editorType;
    private Set<PropertyEditorFacet> facets = new HashSet<PropertyEditorFacet>();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    PropertyMetadata(BeanMetadata beanMetadata, PropertyDescriptor propertyDescriptor)
    {
        this.beanMetadata = beanMetadata;
        this.propertyDescriptor = propertyDescriptor;
        this.labelTextMessageKey = beanMetadata.getBeanClass().getName() + "." + propertyDescriptor.getName();
        this.defaultLabelText = calculateDefaultLabelText(propertyDescriptor);
    }

//**********************************************************************************************************************
// Comparable Implementation
//**********************************************************************************************************************


    public int compareTo(Object o)
    {
        if (o instanceof PropertyMetadata)
        {
            PropertyMetadata other = (PropertyMetadata) o;
            return new Integer(order).compareTo(other.order);
        }
        return 1;
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public BeanMetadata getBeanMetadata()
    {
        return beanMetadata;
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

    public String getPropertyName()
    {
        return propertyDescriptor.getName();
    }

    public Class<?> getPropertyType()
    {
        return propertyDescriptor.getPropertyType();
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

    public PropertyDescriptor getPropertyDescriptor()
    {
        return propertyDescriptor;
    }

    Object writeReplace()
    {
        return new SerializedForm(beanMetadata.getBeanClass().getName(), getPropertyName());
    }

//**********************************************************************************************************************
// Inner Classes
//**********************************************************************************************************************

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
                BeanMetadata beanMetadata = WicketopiaPlugin.get().getBeanMetadataFactory().getBeanMetadata(beanClass);
                return beanMetadata.getPropertyMetadata(propertyName);
            }
            catch (ClassNotFoundException e)
            {
                throw new WicketRuntimeException("Unable to find bean class " + className + ".", e);
            }
        }
    }
}
