package org.wicketopia.context;

import org.apache.wicket.MetaDataKey;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
/**
 * @author James Carman
 */
public class TestContext
{
    @Test
    public void testName()
    {
        final Context context = new Context("blah");
        assertEquals(context.getName(), "blah");
    }

    @Test
    public void testAttributes()
    {
        final MetaDataKey<String> key1 = new MetaDataKey<String>() {};

        final MetaDataKey<String> key2 = new MetaDataKey<String>(){};

        final Context context = new Context("blah");
        context.setAttribute(key1, "value1");
        assertEquals(context.getAttribute(key1), "value1");
        assertNull(context.getAttribute(key2));

    }

}
