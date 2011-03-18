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

package org.wicketopia.editor.context;

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

    public static final String EDIT_TYPE_CREATE = EditorContext.class.getSimpleName() + ".CREATE";
    public static final String EDIT_TYPE_UPDATE = EditorContext.class.getSimpleName() + ".UPDATE";
    private final Map<String, Object> attributes = new TreeMap<String, Object>();
    private final String editType;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static EditorContextPredicate editType(final String... editTypes)
    {
        return new EditorContextPredicate()
        {
            @Override
            public boolean evaluate(EditorContext context)
            {
                for (String editType : editTypes)
                {
                    if(context.getEditType().equals(editType))
                    {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static EditorContextPredicate notEditType(final String... editTypes)
    {
        return new NotPredicate(editType(editTypes));
    }

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
    public <T> T getAttribute(String name)
    {
        return (T) attributes.get(name);
    }

    public <T> void setAttribute(String name, T value)
    {
        attributes.put(name, value);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class NotPredicate implements EditorContextPredicate
    {
        private final EditorContextPredicate predicate;

        private NotPredicate(EditorContextPredicate predicate)
        {
            this.predicate = predicate;
        }

        @Override
        public boolean evaluate(EditorContext context)
        {
            return !predicate.evaluate(context); 
        }
    }
}
