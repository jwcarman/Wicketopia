package org.wicketopia.editor.facet.validator;

import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @author James Carman
 */
public class EmailFacet implements PropertyEditorFacet
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
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public void apply( PropertyEditorBuilder builder )
    {
        builder.addValidator(EmailAddressValidator.getInstance());
    }
}
