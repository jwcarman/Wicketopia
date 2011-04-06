package org.wicketopia.joda.util.format;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.joda.annotation.DateStyle;

/**
 * @since 1.0
 */
public class StyleFormatProvider implements FormatProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final String style;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void decorate(PropertyMetaData propertyMetaData, DateStyle dateStyle)
    {
        propertyMetaData.setFacet(FACET_KEY, new StyleFormatProvider(dateStyle.value()));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public StyleFormatProvider(String style)
    {
        this.style = style;
    }

//----------------------------------------------------------------------------------------------------------------------
// FormatProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public DateTimeFormatter getFormatter()
    {
        return DateTimeFormat.forStyle(style);
    }
}
