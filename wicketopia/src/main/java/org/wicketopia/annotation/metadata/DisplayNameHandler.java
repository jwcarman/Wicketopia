package org.wicketopia.annotation.metadata;

import org.wicketopia.annotation.PropertyMetadataModifierHandler;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @since 1.0
 */
public class DisplayNameHandler implements PropertyMetadataModifierHandler
{
    public void apply( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        final String displayName = ( ( DisplayName ) annotation ).value();
        System.out.println("Changing default label text to " + displayName + "...");
        propertyMetadata.setDefaultLabelText(displayName);
    }
}
