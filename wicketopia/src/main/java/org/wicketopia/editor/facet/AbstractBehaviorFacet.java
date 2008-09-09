package org.wicketopia.editor.facet;

import org.apache.wicket.behavior.IBehavior;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @since 1.0
 */
public abstract class AbstractBehaviorFacet implements PropertyEditorFacet
{
//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract IBehavior createBehavior();

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public void apply( PropertyEditorBuilder builder )
    {
        builder.addBehavior(createBehavior());
    }
}
