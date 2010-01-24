package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.NumberValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.facet.AbstractValidatorFacet;

/**
 * @author James Carman
 * @since 1.0
 */
public class LongRangeFacet extends AbstractValidatorFacet
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final long min;
    private final long max;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public LongRangeFacet(long min, long max)
    {
        this.min = min;
        this.max = max;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    protected IValidator createValidator(EditorContext context)
    {
        if (max != Long.MAX_VALUE && min != Long.MIN_VALUE)
        {
            return NumberValidator.range(min, max);
        }
        else if (max != Long.MAX_VALUE)
        {
            return NumberValidator.maximum(max);
        }
        else
        {
            return NumberValidator.minimum(min);
        }
    }
}