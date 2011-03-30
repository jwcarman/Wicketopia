package org.wicketopia.builder.feature;

import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.context.Context;

import java.io.Serializable;

/**
 * @author James Carman
 */
public interface ComponentBuilderFeature<B extends ComponentBuilder> extends Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void apply(B builder, Context context);
}
