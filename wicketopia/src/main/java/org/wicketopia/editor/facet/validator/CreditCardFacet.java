package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.CreditCardValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.facet.AbstractValidatorFacet;

/**
 * @author James Carman
 */
public class CreditCardFacet extends AbstractValidatorFacet
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final CreditCardFacet instance = new CreditCardFacet();
    
    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static CreditCardFacet getInstance()
    {
        return instance;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    protected IValidator createValidator(EditorContext context)
    {
        return new CreditCardValidator();
    }
}
