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
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Adapts a collection model to be a {@link SortableDataProvider}.  Properties which need to support sorting should
 * be {@link Comparable}.
 * @param <T> the item type
 * @since 1.0
 */
public abstract class SortableCollectionDataProvider<T> extends SortableDataProvider<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String NO_ORDER = "<<<NO_ORDERING>>>";
    private final IModel<? extends Collection<? extends T>> inner;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a data provider which wraps the provided collection model (no default sorting).
     * @param inner the collection model
     */
    public SortableCollectionDataProvider(IModel<? extends Collection<T>> inner)
    {
        this(NO_ORDER, SortOrder.NONE, inner);
    }

    /**
     * Creates a data provider which wraps the provided collection model.  Items will be sorted by #{propertyName} using
     * #{sortOrder}.
     * @param propertyName the sort property
     * @param sortOrder the sort order
     * @param inner the collection model
     */
    public SortableCollectionDataProvider(String propertyName, SortOrder sortOrder, IModel<? extends Collection<? extends T>> inner)
    {
        this.inner = inner;
        setSort(propertyName, sortOrder);
    }

    /**
     * Creates a data provider which wraps the provided collection using {@link Model#of(java.util.Collection)}.
     *
     * @param propertyName the sort property
     * @param sortOrder the sort order
     * @param items the collection
     */
    public SortableCollectionDataProvider(String propertyName, SortOrder sortOrder, Collection<T> items)
    {
        this(propertyName, sortOrder, Model.of(items));
    }

//----------------------------------------------------------------------------------------------------------------------
// IDataProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    public Iterator<? extends T> iterator(int first, int count)
    {
        final List<T> list = new ArrayList<T>(inner.getObject());
        Collections.sort(list, new SortableDataProviderComparator());
        return list.subList(first, first + count).iterator();
    }

    public int size()
    {
        return inner.getObject().size();
    }

//----------------------------------------------------------------------------------------------------------------------
// IDetachable Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void detach()
    {
        super.detach();
        inner.detach();
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class SortableDataProviderComparator implements Comparator<T>, Serializable
    {
        @SuppressWarnings("unchecked")
        public int compare(final T o1, final T o2)
        {
            final String property = getSort().getProperty();
            if (property == null || NO_ORDER.equals(property))
            {
                return -1;
            }
            PropertyModel<Comparable> model1 = new
                    PropertyModel<Comparable>(o1, property);
            PropertyModel<Comparable> model2 = new
                    PropertyModel<Comparable>(o2, property);
            Comparable value1 = model1.getObject();
            Comparable value2 = model2.getObject();
            if (value1 == null)
            {
                return 1;
            }
            if (value2 == null)
            {
                return -1;
            }
            int result = value1.compareTo(value2);

            if (!getSort().isAscending())
            {
                result = -result;
            }

            return result;
        }
    }
}