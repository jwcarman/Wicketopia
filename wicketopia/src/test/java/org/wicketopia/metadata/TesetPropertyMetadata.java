package org.wicketopia.metadata;

import org.testng.annotations.Test;
import org.wicketopia.util.Person;
import static org.testng.Assert.*;

/**
 * @author James Carman
 */
public class TesetPropertyMetadata
{
    @Test
    public void testDefaultLabelText()
    {
        BeanMetadata<Person> beanMetadata = new BeanMetadata<Person>(Person.class);
        PropertyMetadata propertyMetadata = beanMetadata.getPropertyMetadata("first");
        assertEquals(propertyMetadata.getDefaultLabelText(), "First");        
    }


    @Test
    public void testLabelTextMessageKey()
    {
        BeanMetadata<Person> beanMetadata = new BeanMetadata<Person>(Person.class);
        PropertyMetadata propertyMetadata = beanMetadata.getPropertyMetadata("first");
        assertEquals(propertyMetadata.getLabelTextMessageKey(), "org.wicketopia.util.Person.first");        
    }
}
