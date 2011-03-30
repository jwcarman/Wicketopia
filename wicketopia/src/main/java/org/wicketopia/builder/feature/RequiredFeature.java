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

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.context.ContextPredicate;
import org.wicketopia.builder.feature.annotation.required.Optional;
import org.wicketopia.builder.feature.annotation.required.Required;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaFacet;

/**
 * @since 1.0
 */
public class RequiredFeature extends ContextualFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onOptional(PropertyMetaData propertyMetaData, Optional optional)
    {
        WicketopiaFacet.get(propertyMetaData).addEditorFeature(optional(optional.value()));
    }

    @PropertyDecorator
    public static void onRequired(PropertyMetaData propertyMetaData, Required required)
    {
        String[] editTypes = required.value();
        WicketopiaFacet.get(propertyMetaData).addEditorFeature(required(required.value()));
    }

    public static RequiredFeature optional(String... editTypes)
    {
        return new RequiredFeature(whereContextnameNot(editTypes));
    }

    public static RequiredFeature required(String... editTypes)
    {
        return new RequiredFeature(whereContextNameIn(editTypes));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public RequiredFeature(ContextPredicate predicate)
    {
        super(predicate);
    }

//----------------------------------------------------------------------------------------------------------------------
// EditorFeature Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void apply(EditorBuilder editor, Context context)
    {
        editor.setRequired(predicate.evaluate(context));
    }
}
