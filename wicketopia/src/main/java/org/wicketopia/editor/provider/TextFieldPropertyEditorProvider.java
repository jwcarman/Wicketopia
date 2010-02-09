package org.wicketopia.editor.provider;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;

/**
 * @since 1.0
 */
public class TextFieldPropertyEditorProvider implements PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        return FormComponentEditorPanel.createTextFieldPanel(componentId, propertyMetadata, propertyModel);
    }
}
