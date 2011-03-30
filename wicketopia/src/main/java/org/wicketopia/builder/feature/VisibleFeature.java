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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;
import org.wicketopia.builder.feature.annotation.visible.Hidden;
import org.wicketopia.builder.feature.annotation.visible.Visible;
import org.wicketopia.metadata.WicketopiaFacet;

/**
 * @since 1.0
 */
public class VisibleFeature<B extends ComponentBuilder> extends ContextualFeature<B>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger logger = LoggerFactory.getLogger(VisibleFeature.class);

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onHidden(PropertyMetaData propertyMetaData, Hidden hidden)
    {
        WicketopiaFacet.get(propertyMetaData).addEditorFeature(new VisibleFeature<EditorBuilder>(whereContextnameNot(hidden.value())));
        WicketopiaFacet.get(propertyMetaData).addViewerFeature(new VisibleFeature<ViewerBuilder>(whereContextnameNot(hidden.value())));
    }

    @PropertyDecorator
    public static void onVisible(PropertyMetaData propertyMetaData, Visible visible)
    {
        WicketopiaFacet.get(propertyMetaData).addEditorFeature(new VisibleFeature<EditorBuilder>(whereContextNameIn(visible.value())));
        WicketopiaFacet.get(propertyMetaData).addViewerFeature(new VisibleFeature<ViewerBuilder>(whereContextNameIn(visible.value())));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public VisibleFeature(ContextPredicate predicate)
    {
        super(predicate);
    }

//----------------------------------------------------------------------------------------------------------------------
// ComponentBuilderFeature Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void apply(B editor, Context context)
    {
        boolean value = predicate.evaluate(context);
        editor.setViewable(value);
    }
}
