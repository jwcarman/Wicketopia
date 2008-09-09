package org.wicketopia.editor;

import org.apache.wicket.model.IModel;
import org.wicketopia.metadata.PropertyMetadata;

/**
 * @since 1.0
 */
public interface PropertyEditorBuilderFactory
{
    public PropertyEditorBuilder createBuilder( String id, IModel<?> propertyModel, PropertyMetadata propertyMetadata );
}
