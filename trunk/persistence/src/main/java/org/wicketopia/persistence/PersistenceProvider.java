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

package org.wicketopia.persistence;

import java.io.Serializable;
import java.util.List;

public interface PersistenceProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public int getCount(Class<?> beanType);

    public Serializable getIdentifier(Object entity);

    public <T> T getByIdentifier(Class<T> entityType, Serializable identifier);

    public <T> T create(T object);

    public <T> void delete(T object);
    
    public <T> List<T> getList(Class<T> entityType, final int first, final int max, final String sortProperty, final boolean ascending);

    public <T> T update(T object);
}
