package org.wicketopia.joda.component.editor;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.wicketopia.joda.converter.DateTimeFormatSupportConverter;
import org.wicketopia.joda.util.format.JodaFormatSupport;

/**
 * @since 1.0
 */
public class JodaTextField<T> extends TextField<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private JodaFormatSupport<T> formatSupport;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTextField(String id, JodaFormatSupport<T> formatSupport, Class<T> type)
    {
        super(id, type);
        this.formatSupport = formatSupport;
    }

    public JodaTextField(String id, IModel<T> model, JodaFormatSupport<T> formatSupport, Class<T> type)
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
