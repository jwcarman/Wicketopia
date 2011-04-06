package org.wicketopia.joda.component;

import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.wicketopia.joda.util.FormatProvider;

/**
 * @since 1.0
 */
public class LocalDateTextField extends AbstractJodaTimeTextField<LocalDate>
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public LocalDateTextField(String id, FormatProvider formatProvider)
    {
        super(id, formatProvider, LocalDate.class);
    }

    public LocalDateTextField(String id, IModel<LocalDate> model, FormatProvider formatProvider)
    {
        super(id, model, formatProvider, LocalDate.class);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected LocalDate fromDateTime(DateTime date)
    {
        return date.toLocalDate();
    }

    @Override
    protected DateTime toDateTime(LocalDate object)
    {
        return object.toDateTimeAtStartOfDay();
    }
}
