package org.wicketopia.validation.bean;

import org.metastopheles.MetaDataDecorator;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

import javax.validation.constraints.NotNull;

public class BeanValidationPropertyDecorator implements MetaDataDecorator<PropertyMetaData> {
//----------------------------------------------------------------------------------------------------------------------
// MetaDataDecorator Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void decorate(PropertyMetaData propertyMetaData) {
        final WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
        facet.addEditorFeature(new BeanValidationFeature());
        if (facet.getPropertyMetaData().getAnnotation(NotNull.class) != null) {
            facet.setRequired(Context.ALL_CONTEXTS, true);
        }
    }
}
