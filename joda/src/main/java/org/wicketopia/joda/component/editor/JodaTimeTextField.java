package org.wicketopia.joda.component.editor;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.ClientInfo;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.wicketopia.joda.util.translator.DateTimeTranslator;
import org.wicketopia.joda.util.format.FormatProvider;

import java.text.ParseException;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @since 1.0
 */
public class JodaTimeTextField<T> extends TextField<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final int DEFAULT_PIVOT_YEAR = 2000;
    private final boolean applyTimeZoneDifference = true;
    private final FormatProvider formatProvider;
    private final DateTimeTranslator<T> translator;
    private int pivotYear = DEFAULT_PIVOT_YEAR;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTimeTextField(String id, FormatProvider formatProvider, DateTimeTranslator<T> translator, Class<T> type)
    {
        super(id, type);
        this.formatProvider = formatProvider;
        this.translator = translator;
    }

    public JodaTimeTextField(String id, IModel<T> model, FormatProvider formatProvider, DateTimeTranslator<T> translator, Class<T> type)
    {
        super(id, model, type);
        this.formatProvider = formatProvider;
        this.translator = translator;
    }

//----------------------------------------------------------------------------------------------------------------------
// IConverterLocator Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public IConverter getConverter(Class<?> type)
    {
        if (type.equals(getType()))
        {
            return new Converter();
        }
        return super.getConverter(type);
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

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected TimeZone getClientTimeZone()
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
    protected DateTimeZone getTimeZone()
    {
        return DateTimeZone.getDefault();
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class Converter implements IConverter
    {
        @Override
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
                dt.setZone(getTimeZone());
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

        @SuppressWarnings("unchecked")
        public String convertToString(Object object, Locale locale)
        {
            DateTime dt = translator.toDateTime((T) object);

            DateTimeFormatter format = formatProvider.getFormatter();

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
    }
}
