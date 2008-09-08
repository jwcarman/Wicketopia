package org.wicketopia.component.label;

import org.apache.wicket.markup.html.WebPage;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.metadata.PropertyMetadata;

/**
 * @author James Carman
 */
public class PropertyLabelTestPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public PropertyLabelTestPage( PropertyMetadata propertyMetadata )
    {
        add(new PropertyLabel("label", propertyMetadata));
    }
}
