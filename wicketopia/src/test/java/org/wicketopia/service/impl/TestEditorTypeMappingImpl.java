package org.wicketopia.service.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 1.0
 */
public class TestEditorTypeMappingImpl
{
    @Test
    public void testDirectMapping()
    {
        EditorTypeMappingImpl impl = new EditorTypeMappingImpl();
        impl.getEditorTypeMap().put(Integer.class, "integer");
        assertEquals("integer", impl.getEditorType(Integer.class));        
    }

    @Test
    public void testSuperclassMapping()
    {
        EditorTypeMappingImpl impl = new EditorTypeMappingImpl();
        impl.getEditorTypeMap().put(Number.class, "number");
        assertEquals("number", impl.getEditorType(Integer.class));        
    }

    @Test
    public void testNoMapping()
    {
        EditorTypeMappingImpl impl = new EditorTypeMappingImpl();
        impl.getEditorTypeMap().put(Number.class, "number");
        assertNull(impl.getEditorType(String.class));        
    }

    @Test
    public void testSettingMap()
    {
        EditorTypeMappingImpl impl = new EditorTypeMappingImpl();
        final Map<Class,String> map = new HashMap<Class,String>();
        impl.setEditorTypeMap(map);
        assertNull(impl.getEditorType(String.class));
    }
}
