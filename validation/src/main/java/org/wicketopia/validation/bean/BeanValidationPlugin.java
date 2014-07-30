package org.wicketopia.validation.bean;

import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.wicketopia.Wicketopia;
import org.wicketopia.WicketopiaPlugin;

public class BeanValidationPlugin implements WicketopiaPlugin {
//----------------------------------------------------------------------------------------------------------------------
// WicketopiaPlugin Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void initialize(Wicketopia wicketopia) {
        BeanValidationConfiguration config = new BeanValidationConfiguration();
        config.configure(wicketopia.getApplication());
        wicketopia.addPropertyMetaDataDecorator(new BeanValidationPropertyDecorator());
    }
}
