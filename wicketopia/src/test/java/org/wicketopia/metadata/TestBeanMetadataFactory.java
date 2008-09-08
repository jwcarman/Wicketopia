package org.wicketopia.metadata;

import org.testng.annotations.Test;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class TestBeanMetadataFactory
{
    @Test
    public void testWithNoDecorators()
    {
        BeanMetadataFactory.getInstance().getBeanMetadata(Person.class);
    }
}
