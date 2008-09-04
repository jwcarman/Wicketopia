package org.wicketopia.model;

import org.wicketopia.util.Person;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import static org.testng.Assert.*;

/**
 * @author James Carman
 */
public class TestLoadableDetachableEntityModel
{
    @Test
    @SuppressWarnings("unchecked")
    public void testGetObjectWithId()
    {
        final Mockery context = new Mockery();
        final Repository<Person,String> repo = context.mock(Repository.class);
        final Person expected = new Person();
        context.checking(new Expectations(){{
            one(repo).getById("1"); will(returnValue(expected) );
        }});
        LoadableDetachableEntityModel<Person,String> model = new LoadableDetachableEntityModel<Person,String>(repo, "1");
        Person actual = model.getObject();
        assertSame(actual, expected);
        context.assertIsSatisfied();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetObjectWithEntity()
    {
        final Mockery context = new Mockery();
        final Repository<Person,String> repo = context.mock(Repository.class);
        final Person expected = new Person();
        LoadableDetachableEntityModel<Person,String> model = new LoadableDetachableEntityModel<Person,String>(repo, expected);
        Person actual = model.getObject();
        assertSame(actual, expected);
        context.assertIsSatisfied();
    }
}
