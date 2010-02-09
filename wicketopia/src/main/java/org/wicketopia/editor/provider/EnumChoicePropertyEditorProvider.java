package org.wicketopia.editor.provider;

import org.apache.wicket.model.IModel;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

/**
 * @author James Carman
 */
public class EnumChoicePropertyEditorProvider implements PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, WicketopiaPropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        return FormComponentEditorPanel
                .createEnumChoicePanel(componentId, propertyMetadata, propertyModel);
    }
}
