package org.wicketopia.annotation.metadata;

import org.wicketopia.annotation.PropertyMetadataModifierHandler;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class OrderHandler implements PropertyMetadataModifierHandler
{
    public void apply( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        Order order = ( Order ) annotation;
        propertyMetadata.setOrder(order.value());
    }
}
