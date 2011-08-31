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

package org.wicketopia.joda.component.viewer;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketopia.joda.util.format.JodaFormatSupport;
import org.wicketopia.viewer.component.LabelPropertyViewer;

/**
 * @author James Carman
 */
public class JodaLabel<T> extends LabelPropertyViewer
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaLabel(String id, IModel<T> model, JodaFormatSupport<T> formatSupport)
    {
        super(id, new Model<String>(""));
        setDefaultModel(new JodaLabelModel<T>(formatSupport, model));
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private final class JodaLabelModel<T> extends AbstractReadOnlyModel<String>
    {
        private final IModel<T> inner;
        private final JodaFormatSupport<T> formatSupport;

        private JodaLabelModel(JodaFormatSupport<T> formatSupport, IModel<T> inner)
        {
            this.formatSupport = formatSupport;
            this.inner = inner;
        }

        @Override
        public String getObject()
        {
            return formatSupport.convertToString(inner.getObject(), getLocale());
        }

        @Override
        public void detach()
        {
            super.detach();
            inner.detach();
        }
    }
}
