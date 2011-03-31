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

package org.wicketopia.component.label;

import org.apache.wicket.markup.html.basic.Label;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.metadata.WicketopiaPropertyFacet;
import org.wicketopia.model.label.DisplayNameModel;

/**
 * A label which displays the appropriate label text for the property.
 *
 * @see org.wicketopia.model.label.DisplayNameModel the model used by this component
 * @since 1.0
 */
public class PropertyLabel extends Label
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PropertyLabel( String id, PropertyMetaData propertyMetadata )
    {
        super(id, new DisplayNameModel(WicketopiaPropertyFacet.get(propertyMetadata)));
    }
}
