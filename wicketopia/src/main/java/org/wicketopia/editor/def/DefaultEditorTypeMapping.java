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

import org.apache.commons.lang.ClassUtils;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.editor.EditorTypeMapping;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @since 1.0
 */
public class DefaultEditorTypeMapping implements EditorTypeMapping
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private Map<String, String> editorTypeMap = new HashMap<String, String>();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public DefaultEditorTypeMapping()
    {
        addEditorTypeOverride(Byte.class, "byte");
        addEditorTypeOverride(Short.class, "short");
        addEditorTypeOverride(Integer.class, "integer");
        addEditorTypeOverride(Long.class, "long");
        addEditorTypeOverride(Boolean.class, "boolean");
        addEditorTypeOverride(Character.class, "character");
        addEditorTypeOverride(Float.class, "float");
        addEditorTypeOverride(Double.class, "double");

        addEditorTypeOverride(Byte.TYPE, "byte");
        addEditorTypeOverride(Short.TYPE, "short");
        addEditorTypeOverride(Integer.TYPE, "integer");
        addEditorTypeOverride(Long.TYPE, "long");
        addEditorTypeOverride(Boolean.TYPE, "boolean");
        addEditorTypeOverride(Character.TYPE, "character");
        addEditorTypeOverride(Float.TYPE, "float");
        addEditorTypeOverride(Double.TYPE, "double");

        addEditorTypeOverride(String.class, "string");

        addEditorTypeOverride(Date.class, "date");
        addEditorTypeOverride(java.sql.Date.class, "sql_date");
        addEditorTypeOverride(Timestamp.class, "sql_timestamp");

        addEditorTypeOverride(Enum.class, "enum");
    }

//**********************************************************************************************************************
// EditorTypeMapping Implementation
//**********************************************************************************************************************

    public String getEditorType( PropertyMetaData propertyMetadata )
    {
        final Queue<Class> typeQueue = createTypeQueue(propertyMetadata.getPropertyDescriptor().getPropertyType());
        while( !typeQueue.isEmpty() )
        {
            Class type = typeQueue.remove();
            final String editorType = editorTypeMap.get(type.getName());
            if( editorType != null )
            {
                return editorType;
            }
        }
        return null;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @SuppressWarnings( "unchecked" )
    private Queue<Class> createTypeQueue( final Class originalType )
    {
        Queue<Class> queue = new LinkedList<Class>();
        Class currentType = originalType;
        do
        {
            queue.add(currentType);
            currentType = currentType.getSuperclass();
        }
        while( currentType != null );
        queue.addAll(ClassUtils.getAllInterfaces(originalType));
        return queue;
    }

    public void addEditorTypeOverride( Class propertyType, String editorType )
    {
        editorTypeMap.put(propertyType.getName(), editorType);
    }

    public void setEditorTypeOverrides( Map<Class, String> editorTypeOverrides )
    {
        for( Class propertyType : editorTypeOverrides.keySet() )
        {
            editorTypeMap.put(propertyType.getName(), editorTypeOverrides.get(propertyType));
        }
    }
}