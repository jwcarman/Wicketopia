package org.wicketopia.metadata;

import org.wicketopia.metadata.decorator.PropertyMetadataModifierDecorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 1.0
 */
public class BeanMetadataFactory
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final BeanMetadataFactory instance = new BeanMetadataFactory();

    private List<BeanMetadataDecorator> beanMetadataDecorators = new ArrayList<BeanMetadataDecorator>();
    private List<PropertyMetadataDecorator> propertyMetadataDecorators = new ArrayList<PropertyMetadataDecorator>();
    private final Map<Class<?>, BeanMetadata<?>> beanMetadataMap = new HashMap<Class<?>, BeanMetadata<?>>();

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static BeanMetadataFactory getInstance()
    {
        return instance;
    }

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public BeanMetadataFactory()
    {
        propertyMetadataDecorators.add(new PropertyMetadataModifierDecorator());
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public void setBeanMetadataDecorators( List<BeanMetadataDecorator> beanMetadataDecorators )
    {
        this.beanMetadataDecorators = beanMetadataDecorators;
    }

    public void setPropertyMetadataDecorators( List<PropertyMetadataDecorator> propertyMetadataDecorators )
    {
        this.propertyMetadataDecorators = propertyMetadataDecorators;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @SuppressWarnings( "unchecked" )
    public synchronized <T> BeanMetadata<T> getBeanMetadata( Class<T> beanClass )
    {
        BeanMetadata<T> beanMetadata = ( BeanMetadata<T> ) beanMetadataMap.get(beanClass);
        if( beanMetadata == null )
        {
            beanMetadata = new BeanMetadata<T>(beanClass);
            for( BeanMetadataDecorator decorator : beanMetadataDecorators )
            {
                decorator.decorate(beanMetadata);
            }
            for( PropertyMetadata propertyMetadata : beanMetadata.getAllPropertyMetadata() )
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
