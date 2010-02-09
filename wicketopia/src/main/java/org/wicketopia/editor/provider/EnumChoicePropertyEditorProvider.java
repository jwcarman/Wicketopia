package org.wicketopia.editor.provider;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorProvider;

/**
 * @author James Carman
 */
public class EnumChoicePropertyEditorProvider implements PropertyEditorProvider
{
    public PropertyEditor createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        return FormComponentEditorPanel
                .createEnumChoicePanel(componentId, propertyMetadata, propertyModel);
    }
}
