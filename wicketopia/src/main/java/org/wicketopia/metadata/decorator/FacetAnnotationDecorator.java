package org.wicketopia.metadata.decorator;

import org.wicketopia.annotation.FacetAnnotation;
import org.wicketopia.metadata.PropertyMetadata;
import org.wicketopia.metadata.PropertyMetadataDecorator;

import java.lang.annotation.Annotation;

/**
 * @since 1.0
 */
public class FacetAnnotationDecorator implements PropertyMetadataDecorator
{
    public void decorate( PropertyMetadata propertyMetadata )
    {
        for( Annotation annotation : propertyMetadata.getPropertyDescriptor().getReadMethod().getAnnotations() )
        {
            if( annotation.getClass().isAnnotationPresent(FacetAnnotation.class) )
            {
                FacetAnnotation facetAnnotation = annotation.getClass().getAnnotation(FacetAnnotation.class);
                try
                {
                    propertyMetadata.addFacet(facetAnnotation.value().newInstance().handle(annotation));
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
