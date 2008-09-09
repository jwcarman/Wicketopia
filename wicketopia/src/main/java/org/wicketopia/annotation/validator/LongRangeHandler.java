package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.FacetAnnotationHandler;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.facet.validator.LongRangeFacet;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class LongRangeHandler extends FacetAnnotationHandler
{
    protected PropertyEditorFacet createFacet( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        LongRange range = ( LongRange ) annotation;
        return new LongRangeFacet(range.min(), range.max());
    }
}
