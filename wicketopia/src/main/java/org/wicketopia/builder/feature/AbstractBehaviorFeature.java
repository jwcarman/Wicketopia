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

import org.apache.wicket.behavior.IBehavior;
import org.wicketopia.builder.ComponentBuilder;

/**
 * @since 1.0
 */
public abstract class AbstractBehaviorFeature<B extends ComponentBuilder> implements ComponentBuilderFeature<B>
{
//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    protected abstract IBehavior createBehavior();

//----------------------------------------------------------------------------------------------------------------------
// ComponentBuilderFeature Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void activate(B builder)
    {
        builder.addBehavior(createBehavior());
    }
}
