package org.wicketopia.model.label;

import org.apache.wicket.model.ResourceModel;
import org.wicketopia.metadata.PropertyMetadata;

/**
 * @since 1.0
 */
public class PropertyLabelModel extends ResourceModel
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public PropertyLabelModel( PropertyMetadata propertyMetadata )
    {
        super(propertyMetadata.getLabelTextMessageKey(), propertyMetadata.getDefaultLabelText());
    }
}
