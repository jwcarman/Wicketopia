package org.wicketopia.joda.converter;

import org.apache.wicket.util.convert.IConverter;
import org.wicketopia.joda.util.format.DateTimeFormatSupport;

import java.util.Locale;

/**
 * @since 1.0
 */
public class DateTimeFormatSupportConverter<T> implements IConverter
{
    private final DateTimeFormatSupport<T> formatSupport;

    public DateTimeFormatSupportConverter(DateTimeFormatSupport<T> formatSupport)
    {
        this.formatSupport = formatSupport;
    }

    @Override
    public Object convertToObject(String value, Locale locale)
    {
        return formatSupport.convertToObject(value, locale);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String convertToString(Object value, Locale locale)
    {
        return formatSupport.convertToString((T)value, locale);
    }
}
