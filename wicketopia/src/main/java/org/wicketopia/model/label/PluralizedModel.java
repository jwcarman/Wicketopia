package org.wicketopia.model.label;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.wicketopia.util.Pluralizer;

/**
 * @since 1.0
 */
public class PluralizedModel extends AbstractReadOnlyModel<String>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final IModel<String> singular;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PluralizedModel(IModel<String> singular)
    {
        this.singular = singular;
    }

//----------------------------------------------------------------------------------------------------------------------
// IDetachable Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void detach()
    {
        singular.detach();
    }

//----------------------------------------------------------------------------------------------------------------------
// IModel Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public String getObject()
    {
        return Pluralizer.pluralize(singular.getObject());
    }
}
