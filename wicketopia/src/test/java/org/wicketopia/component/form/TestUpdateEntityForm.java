package org.wicketopia.component.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.FormTester;
import org.domdrides.repository.Repository;
import org.jmock.Expectations;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class TestUpdateEntityForm extends AbstractWicketTestCase
{
    private boolean afterUpdateCalled = false;

    @Test
    @SuppressWarnings( "unchecked" )
    public void testWithActualEntity()
    {
        final Repository<Person, String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations()
        {
            {
                one(mockRepo).getById(person.getId());
                will(returnValue(person));
                one(mockRepo).update(person);
                will(returnValue(person));
            }
        });

        tester.startPage(new PersonFormPage()
        {
            protected Form<Person> createPersonForm()
            {
                return new UpdateEntityForm<Person, String>("form", mockRepo, person)
                {
                    @Override
                    protected void afterUpdate( Person entity )
                    {
                        afterUpdateCalled = true;
                    }
                };
            }
        });
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("first", "FirstName");
        formTester.setValue("last", "LastName");
        formTester.submit();
        assertEquals(person.getFirst(), "FirstName");
        assertEquals(person.getLast(), "LastName");
        assertTrue(afterUpdateCalled);
    }


    @Test
    @SuppressWarnings( "unchecked" )
    public void testWithoutOverridingAfterUpdate()
    {
        final Repository<Person, String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations()
        {
            {
                one(mockRepo).getById(person.getId());
                will(returnValue(person));
                one(mockRepo).update(person);
                will(returnValue(person));
            }
        });

        tester.startPage(new PersonFormPage()
        {
            protected Form<Person> createPersonForm()
            {
                return new UpdateEntityForm<Person, String>("form", mockRepo, person);
            }
        });
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("first", "FirstName");
        formTester.setValue("last", "LastName");
        formTester.submit();
        assertEquals(person.getFirst(), "FirstName");
        assertEquals(person.getLast(), "LastName");
    }

    @Test
    @SuppressWarnings( "unchecked" )
    public void testWithEntityId()
    {
        final Repository<Person, String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations()
        {
            {
                one(mockRepo).getById(person.getId());
                will(returnValue(person));
                one(mockRepo).getById(person.getId());
                will(returnValue(person));
                one(mockRepo).update(person);
                will(returnValue(person));
            }
        });

        tester.startPage(new PersonFormPage()
        {
            protected Form<Person> createPersonForm()
            {
                return new UpdateEntityForm<Person, String>("form", mockRepo, person.getId())
                {
                    @Override
                    protected void afterUpdate( Person entity )
                    {
                        afterUpdateCalled = true;
                    }
                };
            }
        });
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("first", "FirstName");
        formTester.setValue("last", "LastName");
        formTester.submit();
        assertEquals(person.getFirst(), "FirstName");
        assertEquals(person.getLast(), "LastName");
        assertTrue(afterUpdateCalled);
    }
}
