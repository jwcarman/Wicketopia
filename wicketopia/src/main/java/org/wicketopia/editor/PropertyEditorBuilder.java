package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.validation.IValidator;

/**
 * @since 1.0
 */
public interface PropertyEditorBuilder
{
    public void addBehavior( IBehavior behavior );

    public void addValidator( IValidator validator );

    public void setRequired( boolean required );

    public Component buildPropertyEditor();
}
