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

package org.wicketopia.editor.annotation;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.editor.annotation.metadata.DisplayName;
import org.wicketopia.editor.annotation.metadata.EditorType;
import org.wicketopia.editor.annotation.metadata.Order;
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
    public static void onOrder(PropertyMetaData propertyMetaData, Order order)
    {
        WicketopiaFacet.get(propertyMetaData).setOrder(order.value());
    }
}
