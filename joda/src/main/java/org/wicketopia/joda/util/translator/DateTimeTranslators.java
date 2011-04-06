package org.wicketopia.joda.util.translator;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * @since 1.0
 */
public class DateTimeTranslators
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static DateTimeTranslator<LocalDate> localDateTranslator()
    {
        return new LocalDateTranslator();
    }

    public static DateTimeTranslator<LocalTime> localTimeTranslator()
    {
        return new LocalTimeTranslator();
    }

    public static DateTimeTranslator<DateTime> noOpTranslator()
    {
        return new NoOpTranslator();
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class LocalDateTranslator implements DateTimeTranslator<LocalDate>
    {
        @Override
        public LocalDate fromDateTime(DateTime date)
        {
            return date.toLocalDate();
        }

        @Override
        public DateTime toDateTime(LocalDate object)
        {
            return object.toDateTimeAtStartOfDay();
        }
    }

    private static class LocalTimeTranslator implements DateTimeTranslator<LocalTime>
    {
        @Override
        public LocalTime fromDateTime(DateTime date)
        {
            return date.toLocalTime();
        }

        @Override
        public DateTime toDateTime(LocalTime object)
        {
            return object.toDateTimeToday();
        }
    }

    private static class NoOpTranslator implements DateTimeTranslator<DateTime>
    {
        @Override
        public DateTime fromDateTime(DateTime dateTime)
        {
            return dateTime;
        }

        @Override
        public DateTime toDateTime(DateTime object)
        {
            return object;
        }
    }
}
