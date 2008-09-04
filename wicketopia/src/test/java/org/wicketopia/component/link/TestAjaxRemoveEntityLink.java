package org.wicketopia.component.link;

import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Person;
import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import org.jmock.Expectations;
import static org.testng.Assert.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * @author James Carman
 */
public class TestAjaxRemoveEntityLink extends AbstractWicketTestCase
{
    private boolean afterRemoveCalled = false;

    @Test
    @SuppressWarnings("unchecked")
    public void testEntityRemovedWhenClicked()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).remove(person);
        }});
        final AjaxRemoveEntityLink<Person,String> link = new AjaxRemoveEntityLink<Person,String>("link", mockRepo, new Model<Person>(person))
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void afterRemove( Person entity, AjaxRequestTarget ajaxRequestTarget )
            {
                afterRemoveCalled = true;
            }
        };
        RemovePersonPage page = new RemovePersonPage(link);
        tester.startPage(page);
        tester.clickLink("link");
        assertTrue(afterRemoveCalled);
    }
}