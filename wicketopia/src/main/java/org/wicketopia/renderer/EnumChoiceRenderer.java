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

package org.wicketopia.renderer;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.StringResourceModel;

/**
 * A choice renderer for enum values which allows internationalization of the display values.  For enum value Bar from
 * enum class com.myco.Foo, it will look for message key com.myco.Foo.Bar.
 *
 * @since 1.0
 */
public class EnumChoiceRenderer<T extends Enum> implements IChoiceRenderer<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = -2873650216055558416L;
    private final DropDownChoice<T> dropDownChoice;

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    private static <T extends Enum> String getEnumDisplayValue(T enumValue, DropDownChoice<T> dropDownChoice)
    {
        return new StringResourceModel(enumValue.getClass().getName() + "." + enumValue.name(), dropDownChoice, null, enumValue.toString()).getString();
    }

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public EnumChoiceRenderer(DropDownChoice<T> dropDownChoice)
    {
        this.dropDownChoice = dropDownChoice;
    }

//**********************************************************************************************************************
// IChoiceRenderer Implementation
//**********************************************************************************************************************


    public Object getDisplayValue(T enumValue)
    {
        return getEnumDisplayValue(enumValue, dropDownChoice);
    }

    public String getIdValue(T enumValue, int index)
    {
        return String.valueOf(enumValue.ordinal());
    }
}
