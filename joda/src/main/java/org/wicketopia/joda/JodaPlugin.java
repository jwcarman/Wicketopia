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

package org.wicketopia.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.wicketopia.Wicketopia;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.joda.provider.editor.JodaTextFieldProvider;
import org.wicketopia.joda.provider.viewer.JodaLabelProvider;
import org.wicketopia.joda.util.format.JodaFormatSupport;
import org.wicketopia.joda.util.translator.DateTimeTranslator;
import org.wicketopia.joda.util.translator.DateTimeTranslators;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @since 1.0
 */
public class JodaPlugin implements WicketopiaPlugin {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String LOCAL_DATE_TYPE = "joda-local-date";
    public static final String LOCAL_TIME_TYPE = "joda-local-time";
    public static final String DATE_TIME_TYPE = "joda-date-time";
    public static final String JAVA_DATE_TYPE = "joda-java-date";
    public static final String JDBC_DATE_TYPE = "joda-jdbc-date";
    public static final String JDBC_TIMESTAMP_TYPE = "joda-jdbc-timestamp";

//----------------------------------------------------------------------------------------------------------------------
// WicketopiaPlugin Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void initialize(Wicketopia wicketopia) {
        registerType(wicketopia, DATE_TIME_TYPE, DateTime.class, DateTimeTranslators.noOpTranslator(), "SS");
        registerType(wicketopia, LOCAL_DATE_TYPE, LocalDate.class, DateTimeTranslators.localDateTranslator(), "S-");
        registerType(wicketopia, LOCAL_TIME_TYPE, LocalTime.class, DateTimeTranslators.localTimeTranslator(), "-S");
        registerType(wicketopia, JAVA_DATE_TYPE, Date.class, DateTimeTranslators.javaDateTranslator(), "S-");
        registerType(wicketopia, JDBC_DATE_TYPE, java.sql.Date.class, DateTimeTranslators.jdbcDateTranslator(), "S-");
        registerType(wicketopia, JDBC_TIMESTAMP_TYPE, Timestamp.class, DateTimeTranslators.jdbcTimestampTranslator(), "SS");
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public <T> void registerType(Wicketopia wicketopia, String typeName, Class<T> propertyType, DateTimeTranslator<T> translator, String style) {
        final JodaFormatSupport<T> formatSupport = new JodaFormatSupport<T>(translator, style);
        wicketopia.addViewerTypeOverride(propertyType, typeName);
        wicketopia.addPropertyViewerProvider(typeName, new JodaLabelProvider<T>(formatSupport));
        wicketopia.addEditorTypeOverride(propertyType, typeName);
        wicketopia.addPropertyEditorProvider(typeName, new JodaTextFieldProvider<T>(formatSupport));
    }
}
