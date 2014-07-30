/*
 * Copyright (c) 2011. Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.metadata;

import org.apache.commons.lang.SerializationUtils;
import org.metastopheles.BeanMetaData;
import org.metastopheles.BeanMetaDataFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.testing.AbstractWicketTestCase;
import org.wicketopia.util.Person;

import static org.testng.Assert.*;

public class TestWicketopiaBeanFacet extends AbstractWicketTestCase {
    @BeforeClass
    public void installWicketopia() {
        new Wicketopia().install(tester.getApplication());
    }

    @Test
    public void testGet() {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaBeanFacet facet = WicketopiaBeanFacet.get(metaData);
        assertNotNull(facet);
    }


    @Test
    public void testDefaultDisplayName() {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaBeanFacet facet = WicketopiaBeanFacet.get(metaData);
        assertEquals(facet.getDisplayName(), "Person");
        facet.setDisplayName("Something Else");
        assertEquals(facet.getDisplayName(), "Something Else");
    }

    @Test
    public void testDefaultDisplayNameMessageKey() {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaBeanFacet facet = WicketopiaBeanFacet.get(metaData);
        assertEquals(facet.getDisplayNameMessageKey(), "org.wicketopia.util.Person");
        facet.setDisplayNameMessageKey("Person");
        assertEquals(facet.getDisplayNameMessageKey(), "Person");
    }

    @Test
    public void testSerialization() {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaBeanFacet facet = WicketopiaBeanFacet.get(metaData);
        assertSame(SerializationUtils.clone(facet), facet);
    }
}
