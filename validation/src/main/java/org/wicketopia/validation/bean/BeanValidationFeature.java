package org.wicketopia.validation.bean;

import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.validation.IValidator;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.builder.feature.validator.AbstractValidatorFeature;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

public class BeanValidationFeature extends AbstractValidatorFeature
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void addBeanValidationFeature(PropertyMetaData propertyMetaData)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new BeanValidationFeature());
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected IValidator<?> createValidator()
    {
        return new PropertyValidator<Object>();
    }
}
