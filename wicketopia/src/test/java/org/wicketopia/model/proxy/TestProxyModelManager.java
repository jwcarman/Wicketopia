/*
 * Copyright (c) 2010 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        assertEquals(proxyFirst.getObject(), "OldFirst");
        IModel<String> proxyLast = mgr.proxy(new PropertyModel<String>(p, "last"));
        assertEquals(proxyLast.getObject(), "OldLast");
        proxyFirst.setObject("NewFirst");
        proxyLast.setObject("NewLast");
        assertEquals(p.getFirst(), "OldFirst");
        assertEquals(p.getLast(), "OldLast");

        mgr.commit();
        assertEquals(p.getFirst(), "NewFirst");
        assertEquals(p.getLast(), "NewLast");

    }
}
