package org.wicketopia.editor.facet;

import org.apache.wicket.validation.IValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @since 1.0
 */
public abstract class AbstractValidatorFacet implements PropertyEditorFacet
{
//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract IValidator createValidator(EditorContext context);

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public final void apply(PropertyEditor builder, EditorContext context)
    {
        final IValidator validator = createValidator(context);
        if (validator != null)
        {
            builder.addValidator(validator);
        }
    }
}
