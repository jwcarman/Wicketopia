package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.validator.NumberValidator;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @since 1.0
 */
public class LongRangeFacet implements PropertyEditorFacet
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

    public LongRangeFacet( long min, long max )
    {
        this.min = min;
        this.max = max;
    }

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public void apply( PropertyEditorBuilder builder )
    {
        if( max != Long.MAX_VALUE && min != Long.MIN_VALUE )
        {
            builder.addValidator(NumberValidator.range(min, max));
        }
        else if( max != Long.MAX_VALUE )
        {
            builder.addValidator(NumberValidator.maximum(max));
        }
        else
        {
            builder.addValidator(NumberValidator.minimum(min));
        }
    }
}