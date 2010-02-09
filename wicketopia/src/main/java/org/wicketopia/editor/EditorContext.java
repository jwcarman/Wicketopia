/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.editor;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class EditorContext implements Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Map<String,Object> attributes = new TreeMap<String,Object>();

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name)
    {
        return (T)attributes.get(name);
    }

    public <T> void setAttribute(String name, T value)
    {
        attributes.put(name, value);
    }
}
