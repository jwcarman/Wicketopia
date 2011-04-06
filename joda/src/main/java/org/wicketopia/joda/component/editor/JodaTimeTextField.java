package org.wicketopia.joda.component.editor;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.wicketopia.joda.converter.DateTimeFormatSupportConverter;
import org.wicketopia.joda.util.format.DateTimeFormatSupport;

import java.util.Locale;

/**
 * @since 1.0
 */
public class JodaTimeTextField<T> extends TextField<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private DateTimeFormatSupport<T> formatSupport;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTimeTextField(String id, DateTimeFormatSupport<T> formatSupport, Class<T> type)
    {
        super(id, type);
        this.formatSupport = formatSupport;
    }

    public JodaTimeTextField(String id, IModel<T> model, DateTimeFormatSupport<T> formatSupport, Class<T> type)
    {
        super(id, model, type);
        this.formatSupport = formatSupport;
    }

//----------------------------------------------------------------------------------------------------------------------
// IConverterLocator Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public IConverter getConverter(Class<?> type)
    {
        if (type.equals(getType()))
        {
            return new DateTimeFormatSupportConverter<T>(formatSupport);
        }
        return super.getConverter(type);
    }
}
