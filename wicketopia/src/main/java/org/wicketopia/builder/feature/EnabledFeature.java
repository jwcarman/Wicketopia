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
import org.wicketopia.builder.feature.annotation.enabled.Disabled;
import org.wicketopia.builder.feature.annotation.enabled.Enabled;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaFacet;

/**
 * @since 1.0
 */
public class EnabledFeature extends ContextualFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onDisabled(PropertyMetaData propertyMetaData, Disabled disabled)
    {
        WicketopiaFacet.get(propertyMetaData).addEditorFeature(new EnabledFeature(whereContextnameNot(disabled.value())));
    }

    @PropertyDecorator
    public static void onEnabled(PropertyMetaData propertyMetaData, Enabled enabled)
    {
        WicketopiaFacet.get(propertyMetaData).addEditorFeature(new EnabledFeature(whereContextNameIn(enabled.value())));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EnabledFeature(ContextPredicate predicate)
    {
        super(predicate);
    }

//----------------------------------------------------------------------------------------------------------------------
// EditorFeature Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void apply(EditorBuilder editor, Context context)
    {
        editor.setEditable(predicate.evaluate(context));
    }
}
