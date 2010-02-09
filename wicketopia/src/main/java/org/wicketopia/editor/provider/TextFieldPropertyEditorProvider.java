package org.wicketopia.editor.provider;

import org.apache.wicket.model.IModel;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

/**
 * @since 1.0
 */
public class TextFieldPropertyEditorProvider implements PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, WicketopiaPropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        return FormComponentEditorPanel.createTextFieldPanel(componentId, propertyMetadata, propertyModel);
    }
}