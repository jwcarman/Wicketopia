package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;

/**
 * @since 1.0
 */
public interface PropertyEditorFactory
{
    public Component createPropertyEditor(String id, PropertyMetaData propertyMetadata, IModel<?> propertyModel, EditorContext context);
}
