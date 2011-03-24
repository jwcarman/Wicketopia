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

package org.wicketopia.editor.decorator;

import org.wicketopia.editor.PropertyEditorDecorator;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.context.EditorContextPredicate;

/**
 * @since 1.0
 */
public abstract class ContextualDecorator implements PropertyEditorDecorator
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final EditorContextPredicate predicate;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ContextualDecorator(EditorContextPredicate predicate)
    {
        this.predicate = predicate;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected static EditorContextPredicate not(EditorContextPredicate predicate)
    {
        return new NotPredicate(predicate);
    }

    protected static EditorContextPredicate whereEditTypeIn(String... editTypes)
    {
        return editTypes == null || editTypes.length == 0 ? new WhereEditTypeIn(EditorContext.ALL_EDIT_TYPES) : new WhereEditTypeIn(editTypes);
    }

    protected static EditorContextPredicate whereEditTypeNotIn(String... editTypes)
    {
        return not(whereEditTypeIn(editTypes));
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class NotPredicate implements EditorContextPredicate
    {
        private final EditorContextPredicate inner;

        private NotPredicate(EditorContextPredicate inner)
        {
            this.inner = inner;
        }

        @Override
        public boolean evaluate(EditorContext context)
        {
            return !inner.evaluate(context);
        }
    }

    private static class WhereEditTypeIn implements EditorContextPredicate
    {
        private final String[] editTypes;

        private WhereEditTypeIn(String... editTypes)
        {
            this.editTypes = editTypes;
        }

        @Override
        public boolean evaluate(EditorContext context)
        {
            String target = context.getEditType();
            for (String editType : editTypes)
            {
                if (EditorContext.ALL_EDIT_TYPES.equals(editType) || target.equals(editType))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
