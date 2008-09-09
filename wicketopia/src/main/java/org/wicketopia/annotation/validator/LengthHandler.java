package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.FacetAnnotationHandler;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.facet.validator.LengthFacet;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class LengthHandler extends FacetAnnotationHandler
{
    protected PropertyEditorFacet createFacet( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        Length length = ( Length ) annotation;
        return new LengthFacet(length.min(), length.max());
    }
}
