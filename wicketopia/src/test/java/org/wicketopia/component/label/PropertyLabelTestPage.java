package org.wicketopia.component.label;

import org.apache.wicket.markup.html.WebPage;
import org.metastopheles.PropertyMetaData;

/**
 * @author James Carman
 */
public class PropertyLabelTestPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public PropertyLabelTestPage( PropertyMetaData propertyMetadata )
    {
        add(new PropertyLabel("label", propertyMetadata));
    }
}
