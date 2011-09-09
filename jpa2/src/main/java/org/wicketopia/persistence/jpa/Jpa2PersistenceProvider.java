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

package org.wicketopia.persistence.jpa;

import org.wicketopia.persistence.PersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author James Carman
 */
public class Jpa2PersistenceProvider implements PersistenceProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @PersistenceContext
    private EntityManager entityManager;

//----------------------------------------------------------------------------------------------------------------------
// PersistenceProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public <T> T create(T object)
    {
        entityManager.persist(object);
        return object;
    }

    @Override
    public <T> void delete(T object)
    {
        entityManager.remove(object);
    }

    @Override
    public <T> T update(T object)
    {
        return entityManager.merge(object);
    }

    @Override
    public <T, C extends Collection<? extends T>> void create(C collection)
    {
        for (T entity : collection)
        {
            entityManager.persist(entity);
        }
    }

    @Override
    public <T, C extends Collection<? extends T>> void delete(C collection)
    {
        for (T entity : collection)
        {
            entityManager.remove(entity);
        }
    }

    @Override
    public <T, C extends Collection<? extends T>> void update(C collection)
    {
        for (T entity : collection)
        {
            entityManager.merge(entity);
        }
    }

    @Override
    public int getCount(Class<?> entityType)
    {
        List results = entityManager.createQuery("select count(*) from " + entityType.getName()).getResultList();
        return ((Number) results.get(0)).intValue();
    }

    @Override
    public Serializable getIdentifier(Object entity)
    {
        throw new UnsupportedOperationException("This feature isn't available until JPA 2.0");
    }

    @Override
    public <T> T getByIdentifier(Class<T> entityType, Serializable identifier)
    {
        return entityManager.find(entityType, identifier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> entityType)
    {
        String jpaql = "select x from " + entityType.getName() + " x";
        final Query query = entityManager.createQuery(jpaql);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(Class<T> entityType, final int first, final int max, final String sortProperty, final boolean ascending)
    {
        String jpaql = "select x from " + entityType.getName() + " x";
        if (sortProperty != null)
        {
            jpaql = jpaql + " order by x." + sortProperty + (ascending ? " asc" : " desc");
        }
        final Query query = entityManager.createQuery(jpaql);
        query.setFirstResult(first).setMaxResults(max);
        return query.getResultList();
    }
}

