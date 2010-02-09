package org.wicketopia.component.label;

import org.apache.wicket.markup.html.basic.Label;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.model.label.PropertyLabelModel;

/**
 * A label which displays the appropriate label text for the property.
 *
 * @see org.wicketopia.model.label.PropertyLabelModel the model used by this component
 * @since 1.0
 */
public class PropertyLabel extends Label
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public PropertyLabel( String id, PropertyMetaData propertyMetadata )
    {
        super(id, new PropertyLabelModel(propertyMetadata));
    }
}
