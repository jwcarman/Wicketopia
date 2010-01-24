package org.wicketopia.editor.def;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;
import org.wicketopia.metadata.PropertyMetadata;
import org.wicketopia.util.Person;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 1.0
 */
public class TestDefaultEditorTypeMapping
{

    @Test
    public void testDefaultMappings()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        /*assertEquals("byte", impl.getEditorType(Byte.class));
        assertEquals("byte", impl.getEditorType(Byte.TYPE));
        assertEquals("short", impl.getEditorType(Short.class));
        assertEquals("short", impl.getEditorType(Short.TYPE));
        assertEquals("integer", impl.getEditorType(Integer.class));
        assertEquals("integer", impl.getEditorType(Integer.TYPE));
        assertEquals("long", impl.getEditorType(Long.class));
        assertEquals("long", impl.getEditorType(Long.TYPE));

        assertEquals("float", impl.getEditorType(Float.class));
        assertEquals("float", impl.getEditorType(Float.TYPE));
        assertEquals("double", impl.getEditorType(Double.class));
        assertEquals("double", impl.getEditorType(Double.TYPE));

        assertEquals("character", impl.getEditorType(Character.class));
        assertEquals("character", impl.getEditorType(Character.TYPE));

        assertEquals("string", impl.getEditorType(String.class));

        assertEquals("date", impl.getEditorType(Date.class));*/
    }

    @Test
    public void testAddOverride()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        impl.addEditorTypeOverride(Integer.class, "integer_override");
        //assertEquals("integer_override", impl.getEditorType(Integer.class));
    }

    @Test
    public void testSetOverrides()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        final Map<Class, String> overrides = new HashMap<Class, String>();
        overrides.put(Integer.class, "integer_override");
        impl.setEditorTypeOverrides(overrides);
        //assertEquals("integer_override", impl.getEditorType(Integer.class));
    }

    @Test
    public void testSuperclassMatch()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        impl.addEditorTypeOverride(RuntimeException.class, "exception");
        //assertEquals("exception", impl.getEditorType(IllegalArgumentException.class));
    }

    @Test
    public void testInterfaceMatch()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        impl.addEditorTypeOverride(Serializable.class, "serial");
        //assertEquals("serial", impl.getEditorType(Person.class));
    }

    @Test
    public void testNoMatch()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        //assertNull(impl.getEditorType(Connection.class));
    }
    /*
    @Test
    public void testSettingMap()
    {
        DefaultEditorTypeMapping impl = new DefaultEditorTypeMapping();
        final Map<Class,String> map = new HashMap<Class,String>();
        impl.setEditorTypeMap(map);
        assertNull(impl.getEditorType(String.class));
    }*/
}
