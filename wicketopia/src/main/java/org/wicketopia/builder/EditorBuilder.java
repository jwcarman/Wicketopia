package org.wicketopia.builder;

import org.apache.wicket.validation.IValidator;

/**
 * @since 1.0
 */
public interface EditorBuilder extends ViewerBuilder
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void addValidator(IValidator validator);

    public void enabled(boolean enabled);

    public void required(boolean required);
}
