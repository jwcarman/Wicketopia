/*
 * Copyright (c) 2011 Carman Consulting, Inc.
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

package org.wicketopia.editor.context;

import org.apache.wicket.MetaDataKey;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class EditorContext implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String ALL_EDIT_TYPES = "<<<ALL>>>";

    public static final String EDIT_TYPE_CREATE = EditorContext.class.getSimpleName() + ".CREATE";
    public static final String EDIT_TYPE_UPDATE = EditorContext.class.getSimpleName() + ".UPDATE";
    private final Map<MetaDataKey<?>, Object> attributes = new TreeMap<MetaDataKey<?>, Object>();
    private final String editType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EditorContext(String editType)
    {
        this.editType = editType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public String getEditType()
    {
        return editType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(MetaDataKey<T> key)
    {
        return (T) attributes.get(key);
    }

    public <T> void setAttribute(MetaDataKey<T> key, T value)
    {
        attributes.put(key, value);
    }
}
