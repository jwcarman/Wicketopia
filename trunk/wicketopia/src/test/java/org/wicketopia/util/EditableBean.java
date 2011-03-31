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

package org.wicketopia.util;

import org.wicketopia.builder.feature.annotation.enabled.Disabled;
import org.wicketopia.builder.feature.annotation.metadata.Order;
import org.wicketopia.builder.feature.annotation.required.Required;
import org.wicketopia.builder.feature.annotation.validator.Length;
import org.wicketopia.builder.feature.annotation.visible.Visible;
import org.wicketopia.context.Context;

import java.io.Serializable;

/**
 * @author James Carman
 */
public class EditableBean implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private String stringProperty;
    private int intProperty;
    private double doubleProperty;
    private Gender gender;

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    @Order(2)
    @Visible(Context.CREATE)
    public double getDoubleProperty()
    {
        return doubleProperty;
    }

    public void setDoubleProperty(double doubleProperty)
    {
        this.doubleProperty = doubleProperty;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    @Order(1)
    @Disabled(Context.CREATE)
    public int getIntProperty()
    {
        return intProperty;
    }

    public void setIntProperty(int intProperty)
    {
        this.intProperty = intProperty;
    }

    @Order(0)
    @Length(min = 5)
    @Required(Context.CREATE)
    public String getStringProperty()
    {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty)
    {
        this.stringProperty = stringProperty;
    }
}
