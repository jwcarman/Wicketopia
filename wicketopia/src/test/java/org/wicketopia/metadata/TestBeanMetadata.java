package org.wicketopia.metadata;

import org.testng.annotations.Test;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class TestBeanMetadata
{
    @Test
    public void testConstructor()
    {
        final BeanMetadata<Person> beanMetadata = new BeanMetadata<Person>(Person.class);
    }
}
