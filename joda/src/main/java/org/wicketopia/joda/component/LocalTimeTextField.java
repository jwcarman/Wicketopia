package org.wicketopia.joda.component;

import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.wicketopia.joda.util.FormatProvider;

/**
 * @since 1.0
 */
public class LocalTimeTextField extends AbstractJodaTimeTextField<LocalTime>
{
    public LocalTimeTextField(String id, FormatProvider formatProvider)
    {
        super(id, formatProvider, LocalTime.class);
    }

    public LocalTimeTextField(String id, IModel<LocalTime> model, FormatProvider formatProvider)
    {
        super(id, model, formatProvider, LocalTime.class);
    }

    @Override
    protected LocalTime fromDateTime(DateTime date)
    {
        return date.toLocalTime();
    }

    @Override
    protected DateTime toDateTime(LocalTime object)
    {
        return object.toDateTimeToday();
    }
}
