package org.wicketopia.component.label;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author James Carman
 */
public class PropertyLabelTestPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public PropertyLabelTestPage( WicketopiaPropertyMetaData propertyMetadata )
    {
        add(new PropertyLabel("label", propertyMetadata));
    }
}
