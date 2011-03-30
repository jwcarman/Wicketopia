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

package org.wicketopia.builder.feature;

import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;

/**
 * @since 1.0
 */
public abstract class ContextualFeature<B extends ComponentBuilder> implements ComponentBuilderFeature<B>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final ContextPredicate predicate;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ContextualFeature(ContextPredicate predicate)
    {
        this.predicate = predicate;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected static ContextPredicate not(ContextPredicate predicate)
    {
        return new NotPredicate(predicate);
    }

    protected static ContextPredicate whereContextNameIn(String... contextNames)
    {
        return contextNames == null || contextNames.length == 0 ? new WhereEditTypeIn(Context.ALL_EDIT_TYPES) : new WhereEditTypeIn(contextNames);
    }

    protected static ContextPredicate whereContextnameNot(String... contextNames)
    {
        return not(whereContextNameIn(contextNames));
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class NotPredicate implements ContextPredicate
    {
        private final ContextPredicate inner;

        private NotPredicate(ContextPredicate inner)
        {
            this.inner = inner;
        }

        @Override
        public boolean evaluate(Context context)
        {
            return !inner.evaluate(context);
        }
    }

    private static class WhereEditTypeIn implements ContextPredicate
    {
        private final String[] editTypes;

        private WhereEditTypeIn(String... editTypes)
        {
            this.editTypes = editTypes;
        }

        @Override
        public boolean evaluate(Context context)
        {
            String target = context.getName();
            for (String editType : editTypes)
            {
                if (Context.ALL_EDIT_TYPES.equals(editType) || target.equals(editType))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
