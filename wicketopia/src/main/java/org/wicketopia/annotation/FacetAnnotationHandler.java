package org.wicketopia.annotation;

import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @since 1.0
 */
public abstract class FacetAnnotationHandler implements PropertyMetadataModifierHandler
{
//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract PropertyEditorFacet createFacet( PropertyMetadata propertyMetadata, Annotation annotation );

//**********************************************************************************************************************
// PropertyMetadataModifierHandler Implementation
//**********************************************************************************************************************

    public final void apply( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        propertyMetadata.addFacet(createFacet(propertyMetadata, annotation));
    }
}
