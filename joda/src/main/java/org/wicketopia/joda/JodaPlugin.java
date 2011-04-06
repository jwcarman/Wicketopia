package org.wicketopia.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.wicketopia.Wicketopia;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.joda.provider.editor.JodaTimeTextFieldProvider;
import org.wicketopia.joda.util.translator.DateTimeTranslators;

/**
 * @since 1.0
 */
public class JodaPlugin implements WicketopiaPlugin
{
    public static final String LOCAL_DATE_TYPE = "joda-local-date";
    public static final String LOCAL_TIME_TYPE = "joda-local-time";
    public static final String DATE_TIME_TYPE = "joda-date-time";

    @Override
    public void initialize(Wicketopia wicketopia)
    {
        // DateTime support...
        wicketopia.addEditorTypeOverride(DateTime.class, DATE_TIME_TYPE);
        wicketopia.addPropertyEditorProvider(DATE_TIME_TYPE, new JodaTimeTextFieldProvider<DateTime>(DateTimeTranslators.noOpTranslator(), "SS"));

        // LocalDate support...
        wicketopia.addEditorTypeOverride(LocalDate.class, LOCAL_DATE_TYPE);
        wicketopia.addPropertyEditorProvider(LOCAL_DATE_TYPE, new JodaTimeTextFieldProvider<LocalDate>(DateTimeTranslators.localDateTranslator(), "S-"));

        // LocalTime support...
        wicketopia.addEditorTypeOverride(LocalTime.class, LOCAL_TIME_TYPE);
        wicketopia.addPropertyEditorProvider(LOCAL_TIME_TYPE, new JodaTimeTextFieldProvider<LocalTime>(DateTimeTranslators.localTimeTranslator(), "-S"));
    }
}
