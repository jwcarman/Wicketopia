package org.wicketopia.metadata;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.beans.Introspector;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * @author James Carman
 */
public class BeanMetadata<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Class<T> beanClass;
    private final BeanInfo beanInfo;
    private final Map<String, PropertyMetadata> propertyMetadataMap = new HashMap<String, PropertyMetadata>();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public BeanMetadata( Class<T> beanClass )
    {
        this.beanClass = beanClass;
        try
        {
            this.beanInfo = Introspector.getBeanInfo(beanClass);
            for( PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors() )
            {
                final String propertyName = propertyDescriptor.getName();
                if( !"class".equals(propertyName) )
                {
                    propertyMetadataMap.put(propertyName, new PropertyMetadata(this, propertyDescriptor));
                }
            }
        }
        catch( IntrospectionException e )
        {
            throw new IllegalArgumentException("Unable to get bean info for class " + beanClass.getName() + ".", e);
        }
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public Class<T> getBeanClass()
    {
        return beanClass;
    }

    public BeanInfo getBeanInfo()
    {
        return beanInfo;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public List<PropertyMetadata> getAllPropertyMetadata()
    {
        final List<PropertyMetadata> propertyMetadatas = new ArrayList<PropertyMetadata>(propertyMetadataMap.values());
        Collections.sort(propertyMetadatas);
        return propertyMetadatas;
    }

    public PropertyMetadata getPropertyMetadata( String propertyName )
    {
        return propertyMetadataMap.get(propertyName);
    }
}
