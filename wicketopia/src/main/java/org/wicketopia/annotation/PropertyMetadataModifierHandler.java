package org.wicketopia.annotation;

import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public interface PropertyMetadataModifierHandler
{
    public void apply( PropertyMetadata propertyMetadata, Annotation annotation );
}
