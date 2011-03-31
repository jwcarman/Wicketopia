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
import org.apache.wicket.validation.validator.PatternValidator;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.builder.feature.annotation.validator.Pattern;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

public class PatternFeature extends AbstractValidatorFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final String pattern;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void decorate(PropertyMetaData propertyMetaData, Pattern pattern)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new PatternFeature(pattern.value()));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PatternFeature(String pattern)
    {
        this.pattern = pattern;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected IValidator<?> createValidator()
    {
        return new PatternValidator(pattern);
    }
}
