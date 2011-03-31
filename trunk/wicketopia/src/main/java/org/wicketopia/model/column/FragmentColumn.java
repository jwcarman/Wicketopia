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

package org.wicketopia.model.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @sine 1.0
 */
public abstract class FragmentColumn<T> extends AbstractColumn<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected FragmentColumn( IModel<String> displayModel )
    {
        super(displayModel);
    }

    protected FragmentColumn( IModel<String> displayModel, String sortProperty )
    {
        super(displayModel, sortProperty);
    }

//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    protected abstract Fragment createFragment( String componentId, IModel<T> model );

//----------------------------------------------------------------------------------------------------------------------
// ICellPopulator Implementation
//----------------------------------------------------------------------------------------------------------------------

    public void populateItem( Item<ICellPopulator<T>> item, String componentId, IModel<T> itemModel )
    {
        item.add(createFragment(componentId, itemModel));
    }
}
