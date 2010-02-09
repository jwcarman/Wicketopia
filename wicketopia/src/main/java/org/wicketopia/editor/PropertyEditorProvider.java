package org.wicketopia.editor;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;

public interface PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel);
}
