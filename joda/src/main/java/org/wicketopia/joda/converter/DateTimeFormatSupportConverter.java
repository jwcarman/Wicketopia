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

package org.wicketopia.joda.converter;

import org.apache.wicket.util.convert.IConverter;
import org.wicketopia.joda.util.format.JodaFormatSupport;

import java.util.Locale;

/**
 * @since 1.0
 */
public class DateTimeFormatSupportConverter<T> implements IConverter<T> {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final JodaFormatSupport<T> formatSupport;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DateTimeFormatSupportConverter(JodaFormatSupport<T> formatSupport) {
        this.formatSupport = formatSupport;
    }

//----------------------------------------------------------------------------------------------------------------------
// IConverter Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public T convertToObject(String value, Locale locale) {
        return formatSupport.convertToObject(value, locale);
    }

    @Override
    public String convertToString(T value, Locale locale) {
        return formatSupport.convertToString(value, locale);
    }
}
