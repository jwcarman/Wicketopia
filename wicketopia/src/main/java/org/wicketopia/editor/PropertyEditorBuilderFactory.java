package org.wicketopia.editor;

import org.apache.wicket.model.IModel;

/**
 * @since 1.0
 */
public interface PropertyEditorBuilderFactory
{
    public PropertyEditorBuilder createBuilder( String id, IModel<?> propertyModel );
}
