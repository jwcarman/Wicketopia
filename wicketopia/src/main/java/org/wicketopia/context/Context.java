/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.context;

import org.apache.wicket.MetaDataKey;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class Context implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------
    public static final String CREATE = "CREATE";
    public static final String UPDATE = "UPDATE";
    public static final String VIEW = "VIEW";
    public static final String LIST = "LIST";

    public static final String ALL_EDIT_TYPES = "<<<ALL>>>";

    private final Map<MetaDataKey<? extends Serializable>, Object> attributes = new HashMap<MetaDataKey<? extends Serializable>, Object>();
    private final String name;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public Context(String name)
    {
        this.name = name;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public String getName()
    {
        return name;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getAttribute(MetaDataKey<T> key)
    {
        return (T) attributes.get(key);
    }

    public <T extends Serializable> void setAttribute(MetaDataKey<T> key, T value)
    {
        attributes.put(key, value);
    }
}
