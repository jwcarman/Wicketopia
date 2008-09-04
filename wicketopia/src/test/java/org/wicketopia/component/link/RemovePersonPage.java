package org.wicketopia.component.link;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.wicketopia.util.Person;
import org.domdrides.repository.Repository;

/**
 * @author James Carman
 */
public class RemovePersonPage extends WebPage
{
    public RemovePersonPage( RemoveEntityLink<Person,String> link )
    {
        add(link);
    }
}
