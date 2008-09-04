package org.wicketopia.component.form;

import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Person;
import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import org.jmock.Expectations;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.markup.html.form.Form;
import static org.testng.Assert.*;

/**
 * @author James Carman
 */
public class TestCreateEntityForm extends AbstractWicketTestCase
{
    private boolean afterCreateCalled = false;

    @Test
    @SuppressWarnings("unchecked")
    public void testSubmitForm()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person prototype = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).add(prototype); will(returnValue(prototype));
        }});

        tester.startPage(new PersonFormPage()
        {
            private static final long serialVersionUID = 1L;

            protected Form<Person> createPersonForm()
            {
                return new CreateEntityForm<Person,String>("form", mockRepo)
                {
                    private static final long serialVersionUID = 1L;

                    protected Person createPrototype()
                    {
                        return prototype;
                    }


                    @Override
                    protected void afterCreate( Person entity )
                    {
                        afterCreateCalled = true;
                    }
                };
            }
        });
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("first", "FirstName");
        formTester.setValue("last", "LastName");
        formTester.submit();
        assertEquals(prototype.getFirst(), "FirstName");
        assertEquals(prototype.getLast(), "LastName");
        assertTrue(afterCreateCalled);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWithoutOverridingAfterCreate()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person prototype = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).add(prototype); will(returnValue(prototype));
        }});

        tester.startPage(new PersonFormPage()
        {
            private static final long serialVersionUID = 1L;

            protected Form<Person> createPersonForm()
            {
                return new CreateEntityForm<Person,String>("form", mockRepo)
                {
                    private static final long serialVersionUID = 1L;

                    protected Person createPrototype()
                    {
                        return prototype;
                    }
                };
            }
        });
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("first", "FirstName");
        formTester.setValue("last", "LastName");
        formTester.submit();
        assertEquals(prototype.getFirst(), "FirstName");
        assertEquals(prototype.getLast(), "LastName");
    }
}
