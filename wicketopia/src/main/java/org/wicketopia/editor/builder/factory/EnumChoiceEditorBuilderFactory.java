package org.wicketopia.editor.builder.factory;

import org.apache.wicket.model.IModel;
import org.wicketopia.component.editor.FormComponentEditorPanel;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorBuilderFactory;
import org.wicketopia.metadata.PropertyMetadata;

/**
 * @author James Carman
 */
public class EnumChoiceEditorBuilderFactory implements PropertyEditorBuilderFactory
{
    @SuppressWarnings( "unchecked" )
    public PropertyEditorBuilder createBuilder( String id, IModel<?> propertyModel, PropertyMetadata propertyMetadata )
    {
        return FormComponentEditorPanel
                .createEnumChoicePanel(id, ( Class<Enum> ) propertyMetadata.getPropertyType(), propertyModel);
    }
}
