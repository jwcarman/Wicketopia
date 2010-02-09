package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

/**
 * @since 1.0
 */
public interface PropertyEditorFactory
{
    public Component createPropertyEditor(String id, WicketopiaPropertyMetaData propertyMetadata, IModel<?> propertyModel, EditorContext context);
}
