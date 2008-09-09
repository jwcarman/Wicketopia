package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.FacetAnnotationHandler;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.facet.validator.RequiredFacet;

import java.lang.annotation.Annotation;

/**
 * @since 1.0
 */
public class RequiredAnnotationHandler implements FacetAnnotationHandler
{
    public PropertyEditorFacet handle( Annotation annotation )
    {
        return RequiredFacet.getInstance();
    }
}
