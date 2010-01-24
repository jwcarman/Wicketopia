package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.facet.AbstractValidatorFacet;

/**
 * @author James Carman
 */
public class EmailFacet extends AbstractValidatorFacet
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

    private static final EmailFacet instance = new EmailFacet();

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static EmailFacet getInstance()
    {
        return instance;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    protected IValidator createValidator(EditorContext context)
    {
        return EmailAddressValidator.getInstance();
    }
}
