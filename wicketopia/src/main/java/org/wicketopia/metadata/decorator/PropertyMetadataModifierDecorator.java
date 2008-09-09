package org.wicketopia.metadata.decorator;

import org.wicketopia.annotation.PropertyMetadataModifier;
import org.wicketopia.metadata.PropertyMetadata;
import org.wicketopia.metadata.PropertyMetadataDecorator;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class PropertyMetadataModifierDecorator implements PropertyMetadataDecorator
{
    public void decorate( PropertyMetadata propertyMetadata )
    {
        for( Annotation annotation : propertyMetadata.getPropertyDescriptor().getReadMethod().getAnnotations() )
        {
            if( annotation.annotationType().isAnnotationPresent(PropertyMetadataModifier.class) )
            {
                PropertyMetadataModifier modifierAnnotation =
                        annotation.annotationType().getAnnotation(PropertyMetadataModifier.class);
                try
                {
                    modifierAnnotation.value().newInstance().apply(propertyMetadata, annotation);
                }
                catch( InstantiationException e )
                {
                    throw new RuntimeException("Unable to construct facet handler.", e);
                }
                catch( IllegalAccessException e )
                {
                    throw new RuntimeException("Unable to construct facet handler.", e);
                }
            }
        }
    }
}
