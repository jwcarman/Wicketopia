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

package org.wicketopia.persistence.model.list;

import org.apache.wicket.model.LoadableDetachableModel;
import org.wicketopia.persistence.PersistenceProvider;

import java.util.List;

public class EntityListModel<T> extends LoadableDetachableModel<List<? extends T>> {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Class<T> entityType;
    private final PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EntityListModel(Class<T> entityType, PersistenceProvider persistenceProvider) {
        this.entityType = entityType;
        this.persistenceProvider = persistenceProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected List<? extends T> load() {
        return persistenceProvider.getAll(entityType);
    }
}
