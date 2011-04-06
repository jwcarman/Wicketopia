package org.wicketopia.joda.util.format;

import org.apache.wicket.IClusterable;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.ClientInfo;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.string.Strings;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.wicketopia.joda.util.translator.DateTimeTranslator;

import java.text.ParseException;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeFormatSupport<T> implements IClusterable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final DateTimeTranslator<T> translator;
    private final FormatProvider formatProvider;

    private boolean applyTimeZoneDifference = true;
    private int pivotYear = 2000;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DateTimeFormatSupport(DateTimeTranslator<T> translator, String defaultStyle)
    {
        this(translator, new StyleFormatProvider(defaultStyle));
    }

    public DateTimeFormatSupport(DateTimeTranslator<T> translator, FormatProvider formatProvider)
    {
        this.translator = translator;
        this.formatProvider = formatProvider;
    }

    public DateTimeFormatSupport(DateTimeTranslator<T> translator, FormatProvider formatProvider, boolean applyTimeZoneDifference, int pivotYear)
    {
        this.translator = translator;
        this.formatProvider = formatProvider;
        this.applyTimeZoneDifference = applyTimeZoneDifference;
        this.pivotYear = pivotYear;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public int getPivotYear()
    {
        return pivotYear;
    }

    public void setPivotYear(int pivotYear)
    {
        this.pivotYear = pivotYear;
    }

    public boolean isApplyTimeZoneDifference()
    {
        return applyTimeZoneDifference;
    }

    public void setApplyTimeZoneDifference(boolean applyTimeZoneDifference)
    {
        this.applyTimeZoneDifference = applyTimeZoneDifference;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public T convertToObject(String value, Locale locale)
    {
        if (Strings.isEmpty(value))
        {
            return null;
        }

        DateTimeFormatter format = formatProvider.getFormatter();

        if (format == null)
        {
            throw new IllegalStateException("format must be not null");
        }
        format.withLocale(locale).withPivotYear(pivotYear);
        if (applyTimeZoneDifference)
        {
            TimeZone zone = getClientTimeZone();
            // instantiate now/ current time
            MutableDateTime dt = new MutableDateTime();
            if (zone != null)
            {
                // set time zone for client
                format = format.withZone(DateTimeZone.forTimeZone(zone));
                dt.setZone(DateTimeZone.forTimeZone(zone));
            }
            try
            {
                // parse date retaining the time of the submission
                int result = format.parseInto(dt, value, 0);
                if (result < 0)
                {
                    throw new ConversionException(new ParseException("unable to parse date " +
                            value, ~result));
                }
            }
            catch (RuntimeException e)
            {
                throw new ConversionException(e);
            }
            // apply the server time zone to the parsed value
            dt.setZone(getServerTimeZone());
            return translator.fromDateTime(dt.toDateTime());
        }
        else
        {
            try
            {
                DateTime date = format.parseDateTime(value);
                return translator.fromDateTime(date);
            }
            catch (RuntimeException e)
            {
                throw new ConversionException(e);
            }
        }
    }

    private static TimeZone getClientTimeZone()
    {
        ClientInfo info = Session.get().getClientInfo();
        if (info instanceof WebClientInfo)
        {
            return ((WebClientInfo) info).getProperties().getTimeZone();
        }
        return null;
    }

    /**
     * Gets the server time zone. Override this method if you want to fix to a certain time zone,
     * regardless of what actual time zone the server is in.
     *
     * @return The server time zone
     */
    private static DateTimeZone getServerTimeZone()
    {
        return DateTimeZone.getDefault();
    }

    @SuppressWarnings("unchecked")
    public String convertToString(T object, Locale locale)
    {
        DateTime dt = translator.toDateTime((T) object);

        DateTimeFormatter format = formatProvider.getFormatter();
        format.withPivotYear(pivotYear).withLocale(locale);
        if (applyTimeZoneDifference)
        {
            TimeZone zone = getClientTimeZone();
            if (zone != null)
            {
                format = format.withZone(DateTimeZone.forTimeZone(zone));
            }
        }
        return format.print(dt);
    }

    public DateTimeFormatSupport<T> withProvider(FormatProvider formatProvider)
    {
        return new DateTimeFormatSupport<T>(translator, formatProvider);
    }
}
