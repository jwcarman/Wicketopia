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

package org.wicketopia.joda.component.editor;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.wicketopia.joda.converter.DateTimeFormatSupportConverter;
import org.wicketopia.joda.util.format.JodaFormatSupport;

/**
 * @since 1.0
 */
public class JodaTextField<T> extends TextField<T> {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private JodaFormatSupport<T> formatSupport;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTextField(String id, JodaFormatSupport<T> formatSupport, Class<T> type) {
        super(id, type);
        this.formatSupport = formatSupport;
    }

    public JodaTextField(String id, IModel<T> model, JodaFormatSupport<T> formatSupport, Class<T> type) {
        super(id, model, type);
        this.formatSupport = formatSupport;
    }

//----------------------------------------------------------------------------------------------------------------------
// IConverterLocator Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public <C> IConverter<C> getConverter(Class<C> type) {
        if (type.equals(getType())) {
            return new DateTimeFormatSupportConverter<C>((JodaFormatSupport<C>) formatSupport);
        }
        return super.getConverter(type);
    }
}
