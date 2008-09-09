package org.wicketopia.component.label;

import org.testng.annotations.Test;
import org.wicketopia.metadata.BeanMetadata;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class TestPropertyLabel extends AbstractWicketTestCase
{
    @Test
    public void testWithMessageKey()
    {
        final BeanMetadata<Person> beanMetadata = new BeanMetadata<Person>(Person.class);
        final PropertyLabelTestPage page = new PropertyLabelTestPage(beanMetadata.getPropertyMetadata("last"));
        tester.startPage(page);

        tester.assertLabel("label", "Last Name (i18n)");
    }

    @Test
    public void testWithoutMessageKey()
    {
        final BeanMetadata<Person> beanMetadata = new BeanMetadata<Person>(Person.class);
        final PropertyLabelTestPage page = new PropertyLabelTestPage(beanMetadata.getPropertyMetadata("first"));
        tester.startPage(page);
        tester.assertLabel("label", "First");
    }

    @Test
    public void testWithoutMessageKeyMultiWord()
    {
        final BeanMetadata<Person> beanMetadata = new BeanMetadata<Person>(Person.class);
        final PropertyLabelTestPage page =
                new PropertyLabelTestPage(beanMetadata.getPropertyMetadata("multiWordProperty"));
        tester.startPage(page);
        tester.assertLabel("label", "Multi Word Property");
    }
}
