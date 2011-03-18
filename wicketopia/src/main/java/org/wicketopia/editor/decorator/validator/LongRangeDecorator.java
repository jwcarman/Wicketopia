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
import org.apache.wicket.validation.validator.NumberValidator;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.decorator.AbstractValidatorDecorator;

/**
 * @author James Carman
 * @since 1.0
 */
public class LongRangeDecorator extends AbstractValidatorDecorator
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;
    private final long min;
    private final long max;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public LongRangeDecorator(long min, long max)
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
        if (max != Long.MAX_VALUE && min != Long.MIN_VALUE)
        {
            return new NumberValidator.RangeValidator(min, max);
        }
        else if (max != Long.MAX_VALUE)
        {
            return new NumberValidator.MaximumValidator(max);
        }
        else
        {
            return new NumberValidator.MinimumValidator(min);
        }
    }
}