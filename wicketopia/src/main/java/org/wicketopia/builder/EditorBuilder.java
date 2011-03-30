package org.wicketopia.builder;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.validation.IValidator;

/**
 * @since 1.0
 */
public interface EditorBuilder extends ViewerBuilder
{
    public void addValidator(IValidator validator);

    public void setEditable(boolean enabled);

    public void setRequired(boolean required);
}
