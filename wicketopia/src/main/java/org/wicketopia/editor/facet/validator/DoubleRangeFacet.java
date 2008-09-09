package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.validator.NumberValidator;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @author James Carman
 */
public class DoubleRangeFacet implements PropertyEditorFacet
{
    private final double min;
    private final double max;

    public DoubleRangeFacet( double min, double max )
    {
        this.min = min;
        this.max = max;
    }

    public void apply( PropertyEditorBuilder builder )
    {
        if( max != Double.MAX_VALUE && min != Double.MIN_VALUE )
        {
            builder.addValidator(NumberValidator.range(min, max));
        }
        else if( max != Double.MAX_VALUE )
        {
            builder.addValidator(NumberValidator.maximum(max));
        }
        else
        {
            builder.addValidator(NumberValidator.minimum(min));
        }
    }
}
