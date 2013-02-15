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

import org.apache.commons.lang.ClassUtils;
import org.metastopheles.PropertyMetaData;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @since 1.0
 */
public class ClassBasedTypeMapping implements TypeMapping
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private Map<String, String> typeMap = new HashMap<String, String>();
    private final String defaultType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ClassBasedTypeMapping()
    {
        this.defaultType = null;
    }

    public ClassBasedTypeMapping(String defaultType)
    {
        this.defaultType = defaultType;
    }

//----------------------------------------------------------------------------------------------------------------------
// TypeMapping Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public String getTypeName(PropertyMetaData propertyMetaData)
    {
        Class<?> propertyType = propertyMetaData.getPropertyDescriptor().getPropertyType();
        return getTypeName(propertyType);
    }

    public String getTypeName(Class<?> propertyType)
    {
        final Queue<Class<?>> typeQueue = createTypeQueue(propertyType);
        while (!typeQueue.isEmpty())
        {
            Class<?> type = typeQueue.remove();
            final String editorType = typeMap.get(type.getName());
            if (editorType != null)
            {
                return editorType;
            }
        }
        return defaultType;
    }
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void addTypeOverride( Class<?> propertyType, String type )
    {
        typeMap.put(propertyType.getName(), type);
    }

    @SuppressWarnings("unchecked")
    private Queue<Class<?>> createTypeQueue(final Class<?> originalType)
    {
        Queue<Class<?>> queue = new LinkedList<Class<?>>();
        Class currentType = originalType;
        do
        {
            queue.add(currentType);
            currentType = currentType.getSuperclass();
        }
        while (currentType != null);
        queue.addAll(ClassUtils.getAllInterfaces(originalType));
        return queue;
    }

    public void setTypeOverrides( Map<Class, String> typeOverrides )
    {
        for (Map.Entry<Class, String> entry : typeOverrides.entrySet())
        {
            typeMap.put(entry.getKey().getName(), entry.getValue());
        }
    }
}
