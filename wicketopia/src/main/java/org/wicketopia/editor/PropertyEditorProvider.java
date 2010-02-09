package org.wicketopia.editor;

import org.apache.wicket.model.IModel;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

public interface PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, WicketopiaPropertyMetaData propertyMetadata, IModel<?> propertyModel);
}
