package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.FacetAnnotationHandler;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.facet.validator.EmailFacet;
import org.wicketopia.metadata.PropertyMetadata;

import java.lang.annotation.Annotation;

/**
 * @author James Carman
 */
public class EmailHandler extends FacetAnnotationHandler
{
    protected PropertyEditorFacet createFacet( PropertyMetadata propertyMetadata, Annotation annotation )
    {
        return EmailFacet.getInstance();
    }
}
