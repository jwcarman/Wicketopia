package org.wicketopia.model.label;

import org.apache.wicket.model.ResourceModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

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

    public PropertyLabelModel( PropertyMetaData propertyMetadata )
    {
        super(WicketopiaPropertyMetaData.get(propertyMetadata).getLabelTextMessageKey(), WicketopiaPropertyMetaData.get(propertyMetadata).getDefaultLabelText());
    }
}
