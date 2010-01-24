package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.NumberValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.facet.AbstractValidatorFacet;

/**
 * @author James Carman
 * @version 1.0
 */
public class DoubleRangeFacet extends AbstractValidatorFacet
{
    private final double min;
    private final double max;

    public DoubleRangeFacet( double min, double max )
    {
        this.min = min;
        this.max = max;
    }

    @Override
    protected IValidator createValidator(EditorContext context)
    {
        if( max != Double.MAX_VALUE && min != Double.MIN_VALUE )
        {
            return NumberValidator.range(min, max);
        }
        else if( max != Double.MAX_VALUE )
        {
            return NumberValidator.maximum(max);
        }
        else
        {
            return NumberValidator.minimum(min);
        }
    }
}
