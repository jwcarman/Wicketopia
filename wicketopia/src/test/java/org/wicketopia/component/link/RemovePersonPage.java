package org.wicketopia.component.link;

import org.apache.wicket.markup.html.WebPage;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class RemovePersonPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public RemovePersonPage( RemoveEntityLink<Person,String> link )
    {
        add(link);
    }
}
