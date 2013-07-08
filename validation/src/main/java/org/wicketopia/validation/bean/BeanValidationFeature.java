package org.wicketopia.validation.bean;

import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.validation.IValidator;
import org.wicketopia.builder.feature.validator.AbstractValidatorFeature;

public class BeanValidationFeature extends AbstractValidatorFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected IValidator<?> createValidator()
    {
        return new PropertyValidator<Object>();
    }
}
