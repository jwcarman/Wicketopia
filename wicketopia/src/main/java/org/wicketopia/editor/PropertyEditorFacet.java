package org.wicketopia.editor;

import java.io.Serializable;

/**
 * @since 1.0
 */
public interface PropertyEditorFacet extends Serializable
{
    public void apply( PropertyEditorBuilder builder );
}
