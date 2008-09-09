package org.wicketopia.editor.facet;

import org.apache.wicket.validation.IValidator;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @since 1.0
 */
public abstract class AbstractValidatorFacet implements PropertyEditorFacet
{
//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract IValidator createValidator();

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public void apply( PropertyEditorBuilder builder )
    {
        builder.addValidator(createValidator());
    }
}
