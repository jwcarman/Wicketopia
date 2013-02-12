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

package org.wicketopia.model.table;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * A @{link SortableCollectionDataProvider} which supports @{link Serializable} items.
 *
 * @param <T> the item type
 * @since 1.0
 */
public class DefaultSortableCollectionDataProvider<T extends Serializable> extends SortableCollectionDataProvider<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DefaultSortableCollectionDataProvider(IModel<? extends Collection<T>> inner)
    {
        super(inner);
    }

    public DefaultSortableCollectionDataProvider(String propertyName, SortOrder sortOrder, IModel<? extends Collection<? extends T>> inner)
    {
        super(propertyName, sortOrder, inner);
    }

    public DefaultSortableCollectionDataProvider(String propertyName, SortOrder sortOrder, Collection<T> items)
    {
        super(propertyName, sortOrder, items);
    }

//----------------------------------------------------------------------------------------------------------------------
// IDataProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Returns a @{link Model} object.
     * @param object the item
     * @return a @{link Model} object
     */
    @Override
    public IModel<T> model(T object)
    {
        return new Model<T>(object);
    }
}
