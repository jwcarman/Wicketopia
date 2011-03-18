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

package org.wicketopia.editor.def;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 1.0
 */
public class TestDefaultEditorTypeMapping
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void testAddOverride()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
        impl.addEditorTypeOverride(Integer.class, "integer_override");
        //assertEquals("integer_override", impl.getEditorType(Integer.class));
    }

    @Test
    public void testDefaultMappings()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
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
    public void testInterfaceMatch()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
        impl.addEditorTypeOverride(Serializable.class, "serial");
        //assertEquals("serial", impl.getEditorType(Person.class));
    }

    @Test
    public void testNoMatch()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
        //assertNull(impl.getEditorType(Connection.class));
    }

    @Test
    public void testSetOverrides()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
        final Map<Class, String> overrides = new HashMap<Class, String>();
        overrides.put(Integer.class, "integer_override");
        impl.setEditorTypeOverrides(overrides);
        //assertEquals("integer_override", impl.getEditorType(Integer.class));
    }

    @Test
    public void testSuperclassMatch()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
        impl.addEditorTypeOverride(RuntimeException.class, "exception");
        //assertEquals("exception", impl.getEditorType(IllegalArgumentException.class));
    }

    /*
    @Test
    public void testSettingMap()
    {
        DefaultPropertyEditorTypeMapping impl = new DefaultPropertyEditorTypeMapping();
        final Map<Class,String> map = new HashMap<Class,String>();
        impl.setEditorTypeMap(map);
        assertNull(impl.getEditorType(String.class));
    }*/
}
