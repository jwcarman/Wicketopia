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

package org.wicketopia.util;

import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ContextualBoolean
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final boolean defaultValue;
    private final List<ContextualCondition> conditions = new LinkedList<ContextualCondition>();
    private final Aggregator aggregator;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ContextualBoolean(boolean defaultValue)
    {
        this.defaultValue = defaultValue;
        this.aggregator = defaultValue ? ContextualBoolean.Aggregator.AND : ContextualBoolean.Aggregator.OR;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public boolean getValue(Context context)
    {
        if (conditions.isEmpty())
        {
            return defaultValue;
        }
        boolean aggregate = defaultValue;
        for (ContextualCondition condition : conditions)
        {
            aggregate = aggregator.aggregate(aggregate, condition.predicate.evaluate(context)? condition.value : !condition.value);
        }
        return aggregate;
    }

    public void setValue(ContextPredicate predicate, boolean value)
    {
        conditions.add(new ContextualCondition(predicate, value));
    }

    public static enum Aggregator
    {
        OR
                {
                    @Override
                    public boolean aggregate(boolean left, boolean right)
                    {
                        return left || right;
                    }
                },
        AND
                {
                    @Override
                    public boolean aggregate(boolean left, boolean right)
                    {
                        return left && right;
                    }
                };

        public abstract boolean aggregate(boolean left, boolean right);


    }
//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private final class ContextualCondition
    {
        private final ContextPredicate predicate;
        private final boolean value;

        private ContextualCondition(ContextPredicate predicate, boolean value)
        {
            this.predicate = predicate;
            this.value = value;
        }
    }
}
