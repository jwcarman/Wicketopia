package org.wicketopia.editor.facet.validator;

import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @since 1.0
 */
public class RequiredFacet implements PropertyEditorFacet
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static RequiredFacet instance = new RequiredFacet();

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static RequiredFacet getInstance()
    {
        return instance;
    }

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    private RequiredFacet()
    {
    }

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public void apply( PropertyEditorBuilder builder )
    {
        builder.setRequired(true);
    }
}
