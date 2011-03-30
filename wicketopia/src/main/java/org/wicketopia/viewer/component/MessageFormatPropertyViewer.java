/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.viewer.component;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.metadata.WicketopiaFacet;
import org.wicketopia.viewer.PropertyViewer;
import org.wicketopia.viewer.PropertyViewerProvider;

/**
 * @since 1.0
 */
public class MessageFormatPropertyViewer extends LabelPropertyViewer implements PropertyViewer
{

    private final String formatPattern;

    public MessageFormatPropertyViewer(String id, IModel<?> model, String formatPattern)
    {
        super(id, model);
        this.formatPattern = formatPattern;
    }


    private static class Provider implements PropertyViewerProvider
    {
        @Override
        public PropertyViewer createPropertyViewer(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
        {
            return new LabelPropertyViewer(componentId, propertyModel);
        }
    }

}
