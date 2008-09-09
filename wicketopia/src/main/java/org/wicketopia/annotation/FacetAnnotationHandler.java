package org.wicketopia.annotation;

import org.wicketopia.editor.PropertyEditorFacet;

import java.lang.annotation.Annotation;

/**
 * @since 1.0
 */
public interface FacetAnnotationHandler
{
    public PropertyEditorFacet handle( Annotation annotation );
}
