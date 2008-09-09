package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.validator.StringValidator;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @author James Carman
 */
public class LengthFacet implements PropertyEditorFacet
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

    public LengthFacet( int min, int max )
    {
        this.min = min;
        this.max = max;
    }

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public void apply( PropertyEditorBuilder builder )
    {
        if( max != Integer.MAX_VALUE && min != Integer.MIN_VALUE )
        {
            if( max == min )
            {
                builder.addValidator(StringValidator.exactLength(min));
            }
            else
            {
                builder.addValidator(StringValidator.lengthBetween(min, max));
            }
        }
        else if( max != Integer.MAX_VALUE )
        {
            builder.addValidator(StringValidator.maximumLength(max));
        }
        else
        {
            builder.addValidator(StringValidator.minimumLength(min));
        }
    }
}
