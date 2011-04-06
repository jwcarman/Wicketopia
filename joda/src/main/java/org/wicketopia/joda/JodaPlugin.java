package org.wicketopia.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.wicketopia.Wicketopia;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.joda.provider.DateTimeTextFieldProvider;
import org.wicketopia.joda.provider.LocalDateTextFieldProvider;
import org.wicketopia.joda.provider.LocalTimeTextFieldProvider;

/**
 * @since 1.0
 */
public class JodaPlugin implements WicketopiaPlugin
{
    @Override
    public void initialize(Wicketopia wicketopia)
    {
        // DateTime support...
        wicketopia.addEditorTypeOverride(DateTime.class, DateTimeTextFieldProvider.TYPE_NAME);
        wicketopia.addPropertyEditorProvider(DateTimeTextFieldProvider.TYPE_NAME, new DateTimeTextFieldProvider());

        // LocalDate support...
        wicketopia.addEditorTypeOverride(LocalDate.class, LocalDateTextFieldProvider.TYPE_NAME);
        wicketopia.addPropertyEditorProvider(LocalDateTextFieldProvider.TYPE_NAME, new LocalDateTextFieldProvider());

        // LocalTime support...
        wicketopia.addEditorTypeOverride(LocalTime.class, LocalTimeTextFieldProvider.TYPE_NAME);
        wicketopia.addPropertyEditorProvider(LocalTimeTextFieldProvider.TYPE_NAME, new LocalTimeTextFieldProvider());

    }
}
