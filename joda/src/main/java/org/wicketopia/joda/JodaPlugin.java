package org.wicketopia.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.wicketopia.Wicketopia;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.joda.provider.DateTimeTextFieldProvider;
import org.wicketopia.joda.provider.LocalDateTextFieldProvider;

/**
 * @since 1.0
 */
public class JodaPlugin implements WicketopiaPlugin
{
    @Override
    public void initialize(Wicketopia wicketopia)
    {
        wicketopia.addEditorTypeOverride(DateTime.class, DateTimeTextFieldProvider.TYPE_NAME);
        wicketopia.addEditorTypeOverride(LocalDate.class, LocalDateTextFieldProvider.TYPE_NAME);
        wicketopia.addPropertyEditorProvider(DateTimeTextFieldProvider.TYPE_NAME, new DateTimeTextFieldProvider());
        wicketopia.addPropertyEditorProvider(LocalDateTextFieldProvider.TYPE_NAME, new LocalDateTextFieldProvider());

    }
}
