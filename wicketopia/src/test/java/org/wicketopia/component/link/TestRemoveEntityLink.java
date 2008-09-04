package org.wicketopia.component.link;

import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Person;
import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import org.jmock.Expectations;
import static org.testng.Assert.*;
import org.apache.wicket.model.Model;

/**
 * @author James Carman
 */
public class TestRemoveEntityLink extends AbstractWicketTestCase
{
    private boolean afterRemoveCalled = false;

    @Test
    public void testEntityRemovedWhenClicked()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).remove(person);
        }});
        final RemoveEntityLink<Person,String> link = new RemoveEntityLink<Person,String>("link", mockRepo, new Model<Person>(person))
        {
            @Override
            protected void afterRemove( Person entity )
            {
                afterRemoveCalled = true;
            }
        };
        RemovePersonPage page = new RemovePersonPage(link);
        tester.startPage(page);
        tester.clickLink("link");
        assertTrue(afterRemoveCalled);        
    }

    @Test
    public void testWithoutOverridingAfterRemoved()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).remove(person);
        }});
        final RemoveEntityLink<Person,String> link = new RemoveEntityLink<Person,String>("link", mockRepo, new Model<Person>(person));
        RemovePersonPage page = new RemovePersonPage(link);
        tester.startPage(page);
        tester.clickLink("link");
    }
}
