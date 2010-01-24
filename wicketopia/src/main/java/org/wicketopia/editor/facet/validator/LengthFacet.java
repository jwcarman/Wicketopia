package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.facet.AbstractValidatorFacet;

/**
 * @author James Carman
 */
public class LengthFacet extends AbstractValidatorFacet
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final int min;
    private final int max;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public LengthFacet(int min, int max)
    {
        this.min = min;
        this.max = max;
    }

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************

    @Override
    protected IValidator createValidator(EditorContext context)
    {
        if (max != Integer.MAX_VALUE && min != Integer.MIN_VALUE)
        {
            if (max == min)
            {
                return StringValidator.exactLength(min);
            }
            else
            {
                return StringValidator.lengthBetween(min, max);
            }
        }
        else if (max != Integer.MAX_VALUE)
        {
            return StringValidator.maximumLength(max);
        }
        else
        {
            return StringValidator.minimumLength(min);
        }
    }
}
