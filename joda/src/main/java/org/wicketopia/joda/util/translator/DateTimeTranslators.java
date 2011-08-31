/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    private static final class JavaDateTranslator implements DateTimeTranslator<Date>
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

    private static final class JdbcDateTranslator implements DateTimeTranslator<java.sql.Date>
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

    private static final class JdbcTimestampTranslator implements DateTimeTranslator<Timestamp>
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

    private static final class LocalDateTranslator implements DateTimeTranslator<LocalDate>
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

    private static final class LocalTimeTranslator implements DateTimeTranslator<LocalTime>
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

    private static final class NoOpTranslator implements DateTimeTranslator<DateTime>
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
