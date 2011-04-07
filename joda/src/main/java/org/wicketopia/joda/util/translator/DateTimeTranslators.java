package org.wicketopia.joda.util.translator;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @since 1.0
 */
public class DateTimeTranslators
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static DateTimeTranslator<Date> javaDateTranslator()
    {
        return new JavaDateTranslator();
    }
    
    public static DateTimeTranslator<java.sql.Date> jdbcDateTranslator()
    {
        return new JdbcDateTranslator();
    }
    
    public static DateTimeTranslator<Timestamp> jdbcTimestampTranslator()
    {
        return new JdbcTimestampTranslator();
    }

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

    private static class JavaDateTranslator implements DateTimeTranslator<Date>
    {
        @Override
        public Date fromDateTime(DateTime dateTime)
        {
            return dateTime.toDate();
        }

        @Override
        public DateTime toDateTime(Date object)
        {
            return new DateTime(object.getTime());
        }
    }

    private static class JdbcDateTranslator implements DateTimeTranslator<java.sql.Date>
    {
        @Override
        public java.sql.Date fromDateTime(DateTime dateTime)
        {
            return new java.sql.Date(dateTime.toDate().getTime());
        }

        @Override
        public DateTime toDateTime(java.sql.Date object)
        {
            return new DateTime(object.getTime());
        }
    }

    private static class JdbcTimestampTranslator implements DateTimeTranslator<Timestamp>
    {
        @Override
        public Timestamp fromDateTime(DateTime dateTime)
        {
            return new Timestamp(dateTime.toDate().getTime());
        }

        @Override
        public DateTime toDateTime(Timestamp object)
        {
            return new DateTime(object.getTime());
        }
    }

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