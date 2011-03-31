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

package org.wicketopia.builder.feature.annotation;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.builder.feature.annotation.enabled.Disabled;
import org.wicketopia.builder.feature.annotation.enabled.Enabled;
import org.wicketopia.builder.feature.annotation.metadata.DisplayName;
import org.wicketopia.builder.feature.annotation.metadata.EditorType;
import org.wicketopia.builder.feature.annotation.metadata.Ignored;
import org.wicketopia.builder.feature.annotation.metadata.Order;
import org.wicketopia.builder.feature.annotation.metadata.ViewerType;
import org.wicketopia.builder.feature.annotation.required.Optional;
import org.wicketopia.builder.feature.annotation.required.Required;
import org.wicketopia.builder.feature.annotation.visible.Hidden;
import org.wicketopia.builder.feature.annotation.visible.Visible;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaFacet;

/**
 * @author James Carman
 * @since 1.0
 */
public class WicketopiaMetaDataDecorators
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onDisabled(PropertyMetaData propertyMetaData, Disabled disabled)
    {
        WicketopiaFacet.get(propertyMetaData).setEnabled(Context.whereContextNameIn(disabled.value()), false);
    }

    @PropertyDecorator
    public static void onDisplayName(PropertyMetaData propertyMetaData, DisplayName displayName)
    {
        WicketopiaFacet.get(propertyMetaData).setDefaultLabelText(displayName.value());
    }

    @PropertyDecorator
    public static void onEditorType(PropertyMetaData propertyMetaData, EditorType editorType)
    {
        WicketopiaFacet.get(propertyMetaData).setEditorType(editorType.value());
    }

    @PropertyDecorator
    public static void onEnabled(PropertyMetaData propertyMetaData, Enabled enabled)
    {
        WicketopiaFacet.get(propertyMetaData).setEnabled(Context.whereContextNameIn(enabled.value()), true);
    }

    @PropertyDecorator
    public static void onHidden(PropertyMetaData propertyMetaData, Hidden hidden)
    {
        WicketopiaFacet.get(propertyMetaData).setVisible(Context.whereContextNameIn(hidden.value()), false);
    }

    @PropertyDecorator
    public static void onIgnored(PropertyMetaData propertyMetaData, Ignored ignored)
    {
        WicketopiaFacet.get(propertyMetaData).setIgnored(true);
    }

    @PropertyDecorator
    public static void onOptional(PropertyMetaData propertyMetaData, Optional optional)
    {
        WicketopiaFacet.get(propertyMetaData).setRequired(Context.whereContextNameIn(optional.value()), false);
    }

    @PropertyDecorator
    public static void onOrder(PropertyMetaData propertyMetaData, Order order)
    {
        WicketopiaFacet.get(propertyMetaData).setOrder(order.value());
    }

    @PropertyDecorator
    public static void onRequired(PropertyMetaData propertyMetaData, Required required)
    {
        WicketopiaFacet.get(propertyMetaData).setRequired(Context.whereContextNameIn(required.value()), true);
    }

    @PropertyDecorator
    public static void onViewerType(PropertyMetaData propertyMetaData, ViewerType viewerType)
    {
        WicketopiaFacet.get(propertyMetaData).setViewerType(viewerType.value());
    }

    @PropertyDecorator
    public static void onVisible(PropertyMetaData propertyMetaData, Visible visible)
    {
        WicketopiaFacet.get(propertyMetaData).setVisible(Context.whereContextNameIn(visible.value()), true);
    }
}
