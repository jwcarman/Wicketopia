package org.wicketopia.metadata;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;

/**
 * @author James Carman
 */
public class PropertyMetadata implements Serializable, Comparable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final PropertyDescriptor propertyDescriptor;

    private String labelTextMessageKey;
    private String defaultLabelText;
    private int order = 0;
    private String editorType = "text";

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    PropertyMetadata( BeanMetadata beanMetadata, PropertyDescriptor propertyDescriptor )
    {
        this.propertyDescriptor = propertyDescriptor;
        this.labelTextMessageKey = beanMetadata.getBeanClass().getName() + "." + propertyDescriptor.getName();
        this.defaultLabelText = calculateDefaultLabelText(propertyDescriptor);
    }

//**********************************************************************************************************************
// Comparable Implementation
//**********************************************************************************************************************

    public int compareTo( Object o )
    {
        if( o instanceof PropertyMetadata )
        {
            PropertyMetadata other = ( PropertyMetadata ) o;
            return new Integer(order).compareTo(other.order);
        }
        return 1;
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public String getDefaultLabelText()
    {
        return defaultLabelText;
    }

    public void setDefaultLabelText( String defaultLabelText )
    {
        this.defaultLabelText = defaultLabelText;
    }

    public String getEditorType()
    {
        return editorType;
    }

    public void setEditorType( String editorType )
    {
        this.editorType = editorType;
    }

    public String getLabelTextMessageKey()
    {
        return labelTextMessageKey;
    }

    public void setLabelTextMessageKey( String labelTextMessageKey )
    {
        this.labelTextMessageKey = labelTextMessageKey;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder( int order )
    {
        this.order = order;
    }

    public PropertyDescriptor getPropertyDescriptor()
    {
        return propertyDescriptor;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    private String calculateDefaultLabelText( PropertyDescriptor propertyDescriptor )
    {
        String[] splits = StringUtils.splitByCharacterTypeCamelCase(propertyDescriptor.getName());
        splits[0] = StringUtils.capitalize(splits[0]);
        return StringUtils.join(splits);
    }
}
