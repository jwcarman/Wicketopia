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

package org.wicketopia.editor.decorator.validator;

import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.MaximumValidator;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.NumberValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.decorator.AbstractValidatorDecorator;

/**
 * @author James Carman
 * @version 1.0
 */
public class DoubleRangeDecorator extends AbstractValidatorDecorator
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final double min;
    private final double max;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DoubleRangeDecorator(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected IValidator<?> createValidator(EditorContext context)
    {
        if (max != Double.MAX_VALUE && min != Double.MIN_VALUE)
        {
            return new NumberValidator.DoubleRangeValidator(min, max);
        }
        else if (max != Double.MAX_VALUE)
        {
            return new NumberValidator.DoubleMaximumValidator(max);
        }
        else
        {
            return new NumberValidator.DoubleMinimumValidator(min);
        }
    }
}
