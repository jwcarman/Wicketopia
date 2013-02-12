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
import java.util.Collection;
import java.util.List;

public interface PersistenceProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    long getCount(Class<?> beanType);

    Serializable getIdentifier(Object entity);

    <T> T getByIdentifier(Class<T> entityType, Serializable identifier);

    <T> T create(T object);

    <T,C extends Collection<? extends T>> void create(C collection);

    <T> void delete(T object);

    <T,C extends Collection<? extends T>> void delete(C collection);

    <T> List<T> getAll(Class<T> entityType);

    <T> List<T> getList(Class<T> entityType, final long first, final long max, final String sortProperty, final boolean ascending);

    <T> T update(T object);

    <T,C extends Collection<? extends T>> void update(C collection);
}
