package org.wicketopia.editor.builder.factory;

import org.apache.wicket.model.IModel;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorBuilderFactory;

/**
 * @since 1.0
 */
public class TextAreaEditorBuilderFactory implements PropertyEditorBuilderFactory
{
    public PropertyEditorBuilder createBuilder( String id, IModel<?> propertyModel )
    {
        return FormComponentEditorPanel.createTextAreaPanel(id, propertyModel);
    }
}
