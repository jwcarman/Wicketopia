package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.FacetAnnotationHandler;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.facet.validator.DoubleRangeFacet;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class DoubleRangeHandler extends FacetAnnotationHandler
{
    protected PropertyEditorFacet createFacet( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        DoubleRange range = ( DoubleRange ) annotation;
        return new DoubleRangeFacet(range.min(), range.max());
    }
}
