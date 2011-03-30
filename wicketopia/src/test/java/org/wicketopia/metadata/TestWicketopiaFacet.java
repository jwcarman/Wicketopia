package org.wicketopia.metadata;

import org.apache.commons.lang.SerializationUtils;
import org.metastopheles.BeanMetaData;
import org.metastopheles.BeanMetaDataFactory;
import org.metastopheles.PropertyMetaData;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import org.wicketopia.util.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author James Carman
 */
public class TestWicketopiaFacet
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void testGet()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet facet = WicketopiaFacet.get(metaData.getPropertyMetaData("first"));
        assertNotNull(facet);
    }

    @Test
    public void testDefaultLabelText()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet facet = WicketopiaFacet.get(metaData.getPropertyMetaData("multiWordProperty"));
        assertEquals(facet.getDefaultLabelText(), "Multi Word Property");
        facet.setDefaultLabelText("Something Else");
        assertEquals(facet.getDefaultLabelText(), "Something Else");
    }

    @Test
    public void testLabelTextMessageKey()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet facet = WicketopiaFacet.get(metaData.getPropertyMetaData("first"));
        assertEquals(facet.getLabelTextMessageKey(), "org.wicketopia.util.Person.first");
        facet.setLabelTextMessageKey("Person.first");
        assertEquals(facet.getLabelTextMessageKey(), "Person.first");
    }

    @Test
    public void testIgnored()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet facet = WicketopiaFacet.get(metaData.getPropertyMetaData("first"));
        assertFalse(facet.isIgnored());
        facet.setIgnored(true);
        assertTrue(facet.isIgnored());
    }

    @Test
    public void testViewerType()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet facet = WicketopiaFacet.get(metaData.getPropertyMetaData("first"));
        assertNull(facet.getViewerType());
        facet.setViewerType("blah");
        assertEquals(facet.getViewerType(), "blah");
    }

    @Test
    public void testEditorType()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet facet = WicketopiaFacet.get(metaData.getPropertyMetaData("first"));
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
        WicketopiaFacet.sort(list);
        assertSame(list.get(0), first);
        assertSame(list.get(1), last);
        assertSame(list.get(2), multi);

        WicketopiaFacet.get(first).setOrder(3);
        WicketopiaFacet.get(multi).setOrder(2);
        WicketopiaFacet.get(last).setOrder(1);
        WicketopiaFacet.sort(list);
        assertSame(list.get(2), first);
        assertSame(list.get(0), last);
        assertSame(list.get(1), multi);

    }

    @Test
    public void testSerialization()
    {
        final BeanMetaDataFactory factory = new BeanMetaDataFactory();
        final BeanMetaData metaData = factory.getBeanMetaData(Person.class);
        WicketopiaFacet first = WicketopiaFacet.get(metaData.getPropertyMetaData("first"));
        assertSame(SerializationUtils.clone(first), first);
    }
}
