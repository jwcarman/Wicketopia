package org.wicketopia.joda.component;

import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;
import org.wicketopia.joda.util.FormatProvider;

/**
 * @since 1.0
 */
public class DateTimeTextField extends AbstractJodaTimeTextField<DateTime>
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DateTimeTextField(String id, FormatProvider formatProvider)
    {
        super(id, formatProvider, DateTime.class);
    }

    public DateTimeTextField(String id, IModel<DateTime> model, FormatProvider formatProvider)
    {
        super(id, model, formatProvider, DateTime.class);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected DateTime fromDateTime(DateTime date)
    {
        return date;
    }

    @Override
    protected DateTime toDateTime(DateTime object)
    {
        return object;
    }
}
