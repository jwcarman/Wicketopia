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

package org.wicketopia.joda.provider.viewer;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.context.Context;
import org.wicketopia.joda.component.viewer.JodaLabel;
import org.wicketopia.joda.util.format.FormatProvider;
import org.wicketopia.joda.util.format.JodaFormatSupport;
import org.wicketopia.viewer.PropertyViewerProvider;

/**
 * @author James Carman
 */
public class JodaLabelProvider<T> implements PropertyViewerProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final JodaFormatSupport<T> formatSupport;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaLabelProvider(JodaFormatSupport<T> formatSupport)
    {
        this.formatSupport = formatSupport;
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyViewerProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public ViewerBuilder createPropertyViewer(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel, Context context)
    {
        final FormatProvider specifiedFormatProvider = propertyMetadata.getFacet(FormatProvider.FACET_KEY);
        return new JodaLabel<T>(componentId, (IModel<T>) propertyModel, specifiedFormatProvider == null ? formatSupport : formatSupport.withProvider(specifiedFormatProvider));
    }
}
