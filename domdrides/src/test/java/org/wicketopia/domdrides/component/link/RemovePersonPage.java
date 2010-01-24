package org.wicketopia.domdrides.component.link;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;

/**
 * @author James Carman
 */
public class RemovePersonPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public RemovePersonPage( AbstractLink link )
    {
        add(link);
    }
}
