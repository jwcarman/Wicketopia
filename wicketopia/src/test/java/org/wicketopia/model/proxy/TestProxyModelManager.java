package org.wicketopia.model.proxy;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.wicketopia.util.Person;

/**
 * @since 1.0
 */
public class TestProxyModelManager
{
    @Test
    public void testCommit()
    {
        final ProxyModelManager mgr = new ProxyModelManager();
        final Person p = new Person();
        p.setFirst("OldFirst");
        p.setLast("OldLast");

        IModel<String> proxyFirst = mgr.proxy(new PropertyModel<String>(p, "first"));
        IModel<String> proxyLast = mgr.proxy(new PropertyModel<String>(p, "last"));
        proxyFirst.setObject("NewFirst");
        proxyLast.setObject("NewLast");
        assertEquals(p.getFirst(), "OldFirst");
        assertEquals(p.getLast(), "OldLast");

        mgr.commit();
        assertEquals(p.getFirst(), "NewFirst");
        assertEquals(p.getLast(), "NewLast");

    }
}
