package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.validation.IValidator;

public interface PropertyEditorBuilder
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void addBehavior(IBehavior behavior);
    public void addValidator(IValidator<?> validator);
    public Component build();
    public void setRequired(boolean required);
}
