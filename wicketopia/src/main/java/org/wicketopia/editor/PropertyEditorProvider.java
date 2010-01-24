package org.wicketopia.editor;

import org.apache.wicket.model.IModel;
import org.wicketopia.metadata.PropertyMetadata;

public interface PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, PropertyMetadata propertyMetadata, IModel<?> propertyModel);
}
