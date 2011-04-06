package org.wicketopia.joda.util.format;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.joda.annotation.DatePattern;

/**
 * @since 1.0
 */
public class PatternFormatProvider implements FormatProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final String datePattern;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void decorate(PropertyMetaData propertyMetaData, DatePattern datePattern)
    {
        propertyMetaData.setFacet(FACET_KEY, new PatternFormatProvider(datePattern.value()));
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PatternFormatProvider(String datePattern)
    {
        this.datePattern = datePattern;
    }

//----------------------------------------------------------------------------------------------------------------------
// FormatProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public DateTimeFormatter getFormatter()
    {
        return DateTimeFormat.forPattern(datePattern);
    }
}
