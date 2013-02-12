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

package org.wicketopia.persistence.model.repeater;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.model.LoadableDetachableEntityModel;

import java.util.Iterator;

/**
 * @author James Carman
 */
public class PersistenceDataProvider<T> extends SortableDataProvider<T,String>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Class<T> beanType;
    private final PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public PersistenceDataProvider(Class<T> beanType, PersistenceProvider persistenceProvider)
    {
        this.beanType = beanType;
        this.persistenceProvider = persistenceProvider;
    }


//----------------------------------------------------------------------------------------------------------------------
// IDataProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    public Iterator<? extends T> iterator(long first, long max)
    {
        final SortParam<String> sort = getSort();
        return persistenceProvider.getList(beanType, first, max, sort == null ? null : sort.getProperty(), sort == null || sort.isAscending()).iterator();
    }

    public IModel<T> model(T entity)
    {
        return new LoadableDetachableEntityModel<T>(beanType, entity, persistenceProvider);
    }

    public long size()
    {
        return persistenceProvider.getCount(beanType);
    }
}
