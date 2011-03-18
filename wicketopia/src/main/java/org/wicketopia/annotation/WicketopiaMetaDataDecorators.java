/*
 * Copyright (c) 2010 Carman Consulting, Inc.
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

package org.wicketopia.annotation;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.annotation.enabled.Disabled;
import org.wicketopia.annotation.enabled.Enabled;
import org.wicketopia.annotation.metadata.DisplayName;
import org.wicketopia.annotation.metadata.EditorType;
import org.wicketopia.annotation.metadata.Order;
import org.wicketopia.annotation.required.Optional;
import org.wicketopia.annotation.required.Required;
import org.wicketopia.annotation.validator.*;
import org.wicketopia.annotation.visible.Hidden;
import org.wicketopia.annotation.visible.Visible;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.decorator.EnabledDecorator;
import org.wicketopia.editor.decorator.RequiredDecorator;
import org.wicketopia.editor.decorator.VisibleDecorator;
import org.wicketopia.editor.decorator.validator.*;
import org.wicketopia.metadata.WicketopiaFacet;

/**
 * @author James Carman
 */
public class WicketopiaMetaDataDecorators
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onCreditCard(PropertyMetaData propertyMetaData, CreditCard creditCard)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(CreditCardDecorator.getInstance());
    }

    @PropertyDecorator
    public static void onDisable(PropertyMetaData propertyMetaData, Disabled disabled)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new EnabledDecorator(EditorContext.notEditType(disabled.value())));
    }

    @PropertyDecorator
    public static void onDisplayName(PropertyMetaData propertyMetaData, DisplayName displayName)
    {
        WicketopiaFacet.get(propertyMetaData).setDefaultLabelText(displayName.value());
    }

    @PropertyDecorator
    public static void onDoubleRange(PropertyMetaData propertyMetaData, DoubleRange doubleRange)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new DoubleRangeDecorator(doubleRange.min(), doubleRange.max()));
    }

    @PropertyDecorator
    public static void onEditorType(PropertyMetaData propertyMetaData, EditorType editorType)
    {
        WicketopiaFacet.get(propertyMetaData).setEditorType(editorType.value());
    }

    @PropertyDecorator
    public static void onEmail(PropertyMetaData propertyMetaData, Email email)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(EmailDecorator.getInstance());
    }

    @PropertyDecorator
    public static void onEnable(PropertyMetaData propertyMetaData, Enabled enabled)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new EnabledDecorator(EditorContext.editType(enabled.value())));
    }

    @PropertyDecorator
    public static void onHidden(PropertyMetaData propertyMetaData, Hidden visible)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new VisibleDecorator(EditorContext.notEditType(visible.value())));
    }

    @PropertyDecorator
    public static void onLength(PropertyMetaData propertyMetaData, Length length)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new LengthDecorator(length.min(), length.max()));
    }

    @PropertyDecorator
    public static void onLongRange(PropertyMetaData propertyMetaData, LongRange longRange)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new LongRangeDecorator(longRange.min(), longRange.max()));
    }

    @PropertyDecorator
    public static void onOptional(PropertyMetaData propertyMetaData, Optional optional)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new RequiredDecorator(EditorContext.notEditType(optional.value())));
    }

    @PropertyDecorator
    public static void onOrder(PropertyMetaData propertyMetaData, Order order)
    {
        WicketopiaFacet.get(propertyMetaData).setOrder(order.value());
    }

    @PropertyDecorator
    public static void onPattern(PropertyMetaData propertyMetaData, Pattern pattern)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new PatternDecorator(pattern.value()));
    }

    @PropertyDecorator
    public static void onRequired(PropertyMetaData propertyMetaData, Required required)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new RequiredDecorator(EditorContext.editType(required.value())));
    }
    
    @PropertyDecorator
    public static void onVisible(PropertyMetaData propertyMetaData, Visible hidden)
    {
        WicketopiaFacet.get(propertyMetaData).addDecorator(new VisibleDecorator(EditorContext.editType(hidden.value())));
    }
}
