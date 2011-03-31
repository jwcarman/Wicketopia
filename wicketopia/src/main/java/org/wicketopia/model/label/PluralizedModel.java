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

package org.wicketopia.model.label;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.wicketopia.util.Pluralizer;

/**
 * @since 1.0
 */
public class PluralizedModel extends AbstractReadOnlyModel<String>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final IModel<String> singular;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PluralizedModel(IModel<String> singular)
    {
        this.singular = singular;
    }

//----------------------------------------------------------------------------------------------------------------------
// IDetachable Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void detach()
    {
        singular.detach();
    }

//----------------------------------------------------------------------------------------------------------------------
// IModel Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public String getObject()
    {
        return Pluralizer.pluralize(singular.getObject());
    }
}
