package org.wicketopia.editor.provider;

import org.apache.wicket.model.IModel;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.metadata.PropertyMetadata;

/**
 * @since 1.0
 */
public class TextAreaPropertyEditorProvider implements PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, PropertyMetadata propertyMetadata, IModel<?> propertyModel)
    {
        return FormComponentEditorPanel.createTextAreaPanel(componentId, propertyMetadata, propertyModel);
    }
}
