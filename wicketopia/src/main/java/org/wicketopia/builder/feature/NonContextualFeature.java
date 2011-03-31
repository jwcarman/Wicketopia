package org.wicketopia.builder.feature;

import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.context.Context;

/**
 * @since 1.0
 */
public abstract class NonContextualFeature<B extends ComponentBuilder> implements ComponentBuilderFeature<B>
{
//----------------------------------------------------------------------------------------------------------------------
// ComponentBuilderFeature Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean isActiveFor(Context context)
    {
        return true;
    }
}
