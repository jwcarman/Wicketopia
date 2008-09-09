package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.validator.CreditCardValidator;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @author James Carman
 */
public class CreditCardFacet implements PropertyEditorFacet
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static CreditCardFacet instance = new CreditCardFacet();
    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static CreditCardFacet getInstance()
    {
        return instance;
    }

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************

    public void apply( PropertyEditorBuilder builder )
    {
        builder.addValidator(new CreditCardValidator());
    }
}
