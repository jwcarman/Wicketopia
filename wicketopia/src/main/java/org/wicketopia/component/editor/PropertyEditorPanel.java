package org.wicketopia.component.editor;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.metadata.PropertyMetadata;

/**
 * @since 1.0
 */
public class PropertyEditorPanel extends Panel
{
    private static final long serialVersionUID = 1L;

    public PropertyEditorPanel( String id, PropertyMetadata propertyMetadata, IModel<?> propertyModel,
                                PropertyEditorFactory propertyEditorFactory )
    {
        super(id);
        add(propertyEditorFactory.createPropertyEditor("editor", propertyMetadata, propertyModel));
    }
}
