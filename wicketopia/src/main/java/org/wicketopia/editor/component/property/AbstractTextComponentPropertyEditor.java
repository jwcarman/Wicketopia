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

package org.wicketopia.editor.component.property;

import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.metastopheles.PropertyMetaData;

/**
 * @since 1.0
 */
public abstract class AbstractTextComponentPropertyEditor extends AbstractFormComponentPropertyEditor
{
    protected AbstractTextComponentPropertyEditor(String id, PropertyMetaData propertyMetaData, AbstractTextComponent formComponent)
    {
        super(id, propertyMetaData, formComponent);
        Class<?> propertyType = propertyMetaData.getPropertyDescriptor().getPropertyType();
        formComponent.setType(propertyType);
        if(propertyType.isPrimitive())
        {
            formComponent.setRequired(true);
        }
    }
}
