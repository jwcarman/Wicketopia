package org.wicketopia.annotation.metadata;

import org.wicketopia.annotation.PropertyMetadataModifierHandler;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class EditorTypeHandler implements PropertyMetadataModifierHandler
{
    public void apply( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        propertyMetadata.setEditorType(( ( EditorType ) annotation ).value());
    }
}
