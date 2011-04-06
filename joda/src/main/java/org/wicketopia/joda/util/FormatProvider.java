package org.wicketopia.joda.util;

import org.apache.wicket.IClusterable;
import org.joda.time.format.DateTimeFormatter;
import org.metastopheles.FacetKey;

/**
 * @since 1.0
 */
public interface FormatProvider extends IClusterable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final FacetKey<FormatProvider> FACET_KEY = new FacetKey<FormatProvider>() {};

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public DateTimeFormatter getFormatter();
}
