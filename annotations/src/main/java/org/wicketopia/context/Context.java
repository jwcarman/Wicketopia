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

package org.wicketopia.context;

import java.io.Serializable;

/**
 *
 */
public class Context implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String ALL = "***ALL***";

    public static final String CREATE = "CREATE";
    public static final String UPDATE = "UPDATE";
    public static final String VIEW = "VIEW";
    public static final String LIST = "LIST";


    public static final ContextPredicate ALL_CONTEXTS = new AllContextsPredicate();

    private final String name;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static ContextPredicate whereContextNameIn(String... contextNames)
    {
        return new ContextNamePredicate(contextNames);
    }

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
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static final class AllContextsPredicate implements ContextPredicate
    {
        @Override
        public boolean evaluate(Context context)
        {
            return true;
        }
    }
    
    private static final class ContextNamePredicate implements ContextPredicate
    {
        private final String[] contextNames;

        private ContextNamePredicate(String... contextNames)
        {
            this.contextNames = contextNames;
        }

        @Override
        public boolean evaluate(Context context)
        {
            if (contextNames == null || contextNames.length == 0)
            {
                return true;
            }
            else
            {
                final String target = context.getName();
                for (String contextName : contextNames)
                {
                    if (ALL.equals(contextName) || target.equals(contextName))
                    {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
