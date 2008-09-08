package org.wicketopia.metadata;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * @author James Carman
 */
public class BeanMetadataFactory
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final BeanMetadataFactory instance = new BeanMetadataFactory();
    
    private List<BeanMetadataDecorator> beanMetadataDecorators = new ArrayList<BeanMetadataDecorator>();
    private List<PropertyMetadataDecorator> propertyMetadataDecorators = new ArrayList<PropertyMetadataDecorator>();
    private final Map<Class<?>,BeanMetadata<?>> beanMetadataMap = new HashMap<Class<?>,BeanMetadata<?>>();

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static BeanMetadataFactory getInstance()
    {
        return instance;
    }

    public void setBeanMetadataDecorators( List<BeanMetadataDecorator> beanMetadataDecorators )
    {
        this.beanMetadataDecorators = beanMetadataDecorators;
    }

    public void setPropertyMetadataDecorators( List<PropertyMetadataDecorator> propertyMetadataDecorators )
    {
        this.propertyMetadataDecorators = propertyMetadataDecorators;
    }

    @SuppressWarnings("unchecked")
    public synchronized <T> BeanMetadata<T> getBeanMetadata(Class<T> beanClass)
    {
        BeanMetadata<T> beanMetadata = (BeanMetadata<T>)beanMetadataMap.get(beanClass);
        if(beanMetadata == null)
        {
            beanMetadata = new BeanMetadata<T>(beanClass);
            for( BeanMetadataDecorator decorator : beanMetadataDecorators )
            {
                decorator.decorate(beanMetadata);   
            }
            for( PropertyMetadata propertyMetadata: beanMetadata.getAllPropertyMetadata() )
            {
                for( PropertyMetadataDecorator decorator : propertyMetadataDecorators )
                {
                    decorator.decorate(propertyMetadata);
                }
            }
            beanMetadataMap.put(beanClass, beanMetadata);
        }
        return beanMetadata;
    }
}
