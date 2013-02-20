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

package org.wicketopia.builder.feature.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.annotation.validator.Length;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

/**
 * @author James Carman
 */
public class LengthFeature extends AbstractValidatorFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;
    private final int min;
    private final int max;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void decorate(PropertyMetaData propertyMetaData, Length length)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new LengthFeature(length.min(), length.max()));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public LengthFeature(int min, int max)
    {
        this.min = min;
        this.max = max;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected IValidator<?> createValidator()
    {
        if (max != Integer.MAX_VALUE && min != Integer.MIN_VALUE)
        {
            if (max == min)
            {
                return StringValidator.exactLength(min);
            }
            else
            {
                return StringValidator.lengthBetween(min, max);
            }
        }
        else if (max != Integer.MAX_VALUE)
        {
            return StringValidator.maximumLength(max);
        }
        else
        {
            return StringValidator.minimumLength(min);
        }
    }
}
