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

package org.wicketopia.mapping;

import org.metastopheles.BeanMetaData;
import org.metastopheles.BeanMetaDataFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wicketopia.util.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author James Carman
 */
public class TestClassBasedTypeMapping
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private ClassBasedTypeMapping typeMapping;
    private BeanMetaData metaData;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @BeforeMethod
    protected void setUp() throws Exception
    {
        typeMapping = new ClassBasedTypeMapping();
        BeanMetaDataFactory metaDataFactory = new BeanMetaDataFactory();
        metaData = metaDataFactory.getBeanMetaData(Person.class);
    }

    @Test
    public void testAddOverride()
    {
        typeMapping.addTypeOverride(Integer.class, "integer_override");
        assertEquals(typeMapping.getTypeName(metaData.getPropertyMetaData("ssn")), "integer_override");
    }

    /*@Test
    public void testDefaultMappings()
    {
        ClassBasedTypeMapping impl = new ClassBasedTypeMapping();
        assertEquals("byte", impl.getTypeName(Byte.class));
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

        assertEquals("date", impl.getEditorType(Date.class));
    }
*/
    @Test
    public void testInterfaceMatch()
    {
        typeMapping.addTypeOverride(Serializable.class, "serial");
        assertEquals("serial", typeMapping.getTypeName(metaData.getPropertyMetaData("first")));
    }

    @Test
    public void testNoMatchNoDefault()
    {
        assertNull(typeMapping.getTypeName(metaData.getPropertyMetaData("first")));
    }

    @Test
    public void testNoMatchWithDefault()
    {
        ClassBasedTypeMapping impl = new ClassBasedTypeMapping("default");
        assertEquals(impl.getTypeName(metaData.getPropertyMetaData("first")), "default");
    }

    @Test
    public void testSetOverrides()
    {
        ClassBasedTypeMapping impl = new ClassBasedTypeMapping();
        final Map<Class, String> overrides = new HashMap<Class, String>();
        overrides.put(Integer.class, "integer_override");
        impl.setTypeOverrides(overrides);
        assertEquals(impl.getTypeName(metaData.getPropertyMetaData("ssn")), "integer_override");
    }

    @Test
    public void testSuperclassMatch()
    {
        ClassBasedTypeMapping impl = new ClassBasedTypeMapping();
        impl.addTypeOverride(Number.class, "num");
        assertEquals(impl.getTypeName(metaData.getPropertyMetaData("ssn")), "num");
    }
}
