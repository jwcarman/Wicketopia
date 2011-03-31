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
import org.apache.wicket.validation.validator.CreditCardValidator;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.builder.feature.annotation.validator.CreditCard;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

/**
 * @author James Carman
 */
public class CreditCardFeature extends AbstractValidatorFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final CreditCardFeature instance = new CreditCardFeature();

    private static final long serialVersionUID = 1L;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void decorate(PropertyMetaData propertyMetaData, CreditCard creditCard)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(CreditCardFeature.getInstance());
    }

    public static CreditCardFeature getInstance()
    {
        return instance;
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    private CreditCardFeature()
    {
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected IValidator<?> createValidator()
    {
        return new CreditCardValidator();
    }
}
