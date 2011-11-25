/*
 * Copyright (c) 2011 Carman Consulting, Inc.
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
import org.metastopheles.PropertyMetaData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.testing.AbstractWicketTestCase;
import org.wicketopia.util.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author James Carman
 */
public class TestWicketopiaPropertyFacet extends AbstractWicketTestCase
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @BeforeClass
    public void installWicketopia()
    {
        new Wicketopia().install(tester.getApplication());
    }

    @Test
    public void testGet()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("first"));
        assertNotNull(facet);
    }

    @Test
    public void testDefaultLabelText()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("multiWordProperty"));
        assertEquals(facet.getDisplayName(), "Multi Word Property");
        facet.setDisplayName("Something Else");
        assertEquals(facet.getDisplayName(), "Something Else");
    }

    @Test
    public void testLabelTextMessageKey()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("first"));
        assertEquals(facet.getDisplayNameMessageKey(), "org.wicketopia.util.Person.first");
        facet.setDisplayNameMessageKey("Person.first");
        assertEquals(facet.getDisplayNameMessageKey(), "Person.first");
    }

    @Test
    public void testIgnored()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("first"));
        assertFalse(facet.isIgnored());
        facet.setIgnored(true);
        assertTrue(facet.isIgnored());
    }

    @Test
    public void testViewerType()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("first"));
        assertNull(facet.getViewerType());
        facet.setViewerType("blah");
        assertEquals(facet.getViewerType(), "blah");
    }

    @Test
    public void testEditorType()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("first"));
        assertNull(facet.getEditorType());
        facet.setEditorType("blah");
        assertEquals(facet.getEditorType(), "blah");
    }

    @Test
    public void testSortPropertyMetaData()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        PropertyMetaData first = metaData.getPropertyMetaData("first");
        PropertyMetaData last = metaData.getPropertyMetaData("last");
        PropertyMetaData multi = metaData.getPropertyMetaData("multiWordProperty");
        final List<PropertyMetaData> list = new ArrayList<PropertyMetaData>(Arrays.asList(first, last, multi));
        WicketopiaPropertyFacet.sort(list);
        assertSame(list.get(0), first);
        assertSame(list.get(1), last);
        assertSame(list.get(2), multi);

        WicketopiaPropertyFacet.get(first).setOrder(3);
        WicketopiaPropertyFacet.get(multi).setOrder(2);
        WicketopiaPropertyFacet.get(last).setOrder(1);
        WicketopiaPropertyFacet.sort(list);
        assertSame(list.get(2), first);
        assertSame(list.get(0), last);
        assertSame(list.get(1), multi);

    }

    @Test
    public void testSerialization()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaPropertyFacet first = WicketopiaPropertyFacet.get(metaData.getPropertyMetaData("first"));
        assertSame(SerializationUtils.clone(first), first);
    }
}
