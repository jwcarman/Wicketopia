package org.wicketopia.metadata;

import org.apache.wicket.WicketRuntimeException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;

/**
 * @since 1.0
 */
public class BeanMetadata<T> implements Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final Class<T> beanClass;
    private final BeanInfo beanInfo;
    private final Map<String, PropertyMetadata> propertyMetadataMap = new HashMap<String, PropertyMetadata>();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public BeanMetadata(Class<T> beanClass)
    {
        this.beanClass = beanClass;
        try
        {
            this.beanInfo = Introspector.getBeanInfo(beanClass);
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors())
            {
                final String propertyName = propertyDescriptor.getName();
                if (!"class".equals(propertyName))
                {
                    propertyMetadataMap.put(propertyName, new PropertyMetadata(this, propertyDescriptor));
                }
            }
        }
        catch (IntrospectionException e)
        {
            throw new WicketRuntimeException("Unable to get bean info for class " + beanClass.getName() + ".", e);
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

    public List<PropertyMetadata> getAllPropertyMetadata(String... skippedProperties)
    {
        final List<PropertyMetadata> propertyMetadatas = new ArrayList<PropertyMetadata>(propertyMetadataMap.size() - skippedProperties.length);

        final Set<String> skipped = new TreeSet<String>();
        skipped.addAll(Arrays.asList(skippedProperties));
        for (String propertyName : propertyMetadataMap.keySet())
        {
            if(!skipped.contains(propertyName))
            {
                propertyMetadatas.add(propertyMetadataMap.get(propertyName));
            }
        }
        Collections.sort(propertyMetadatas);
        return propertyMetadatas;
    }

    public PropertyMetadata getPropertyMetadata(String propertyName)
    {
        final PropertyMetadata propertyMetadata = propertyMetadataMap.get(propertyName);
        if(propertyMetadata == null)
        {
            throw new WicketRuntimeException("Property " + propertyName + " not found on bean class " + beanClass.getName());
        }
        return propertyMetadata;
    }
}
