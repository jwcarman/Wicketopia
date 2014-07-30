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

package org.wicketopia.persistence.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.wicketopia.persistence.PersistenceProvider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * @author James Carman
 */
public class HibernatePersistenceProvider implements PersistenceProvider {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final String ASSOCIATION_ALIAS = "sp";
    private SessionFactory sessionFactory;

//----------------------------------------------------------------------------------------------------------------------
// PersistenceProvider Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public <T> T create(T object) {
        getSession().save(object);
        return object;
    }

    @Override
    public <T> void delete(T object) {
        getSession().delete(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> entityType) {
        return getSession().createCriteria(entityType).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getByIdentifier(Class<T> beanType, Serializable identifier) {
        return (T) getSession().get(beanType, identifier);
    }

    @Override
    public long getCount(Class<?> beanType) {
        return ((Number) getSession().createCriteria(beanType).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    public Serializable getIdentifier(Object entity) {
        return getSession().getIdentifier(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(Class<T> entityType, long first, long max, String sortProperty, boolean ascending) {
        Criteria c = getSession().createCriteria(entityType)
                .setMaxResults((int) max)
                .setFirstResult((int) first);
        if (sortProperty != null) {
            final int ndx = sortProperty.lastIndexOf('.');
            if (ndx != -1) {
                final String associationPath = sortProperty.substring(0, ndx);
                final String propertyName = sortProperty.substring(ndx + 1);
                c = c.createAlias(associationPath, ASSOCIATION_ALIAS)
                        .addOrder(ascending ? Order.asc(ASSOCIATION_ALIAS + "." + propertyName) : Order.desc(ASSOCIATION_ALIAS + "." + propertyName));
            } else {
                c = c.addOrder(ascending ? Order.asc(sortProperty) : Order.desc(sortProperty));
            }
        }
        return c.list();
    }

    @Override
    public <T> T update(T object) {
        getSession().update(object);
        return object;
    }

    @Override
    public <T, C extends Collection<? extends T>> void create(C collection) {
        for (T entity : collection) {
            getSession().save(entity);
        }
    }

    @Override
    public <T, C extends Collection<? extends T>> void delete(C collection) {
        for (T entity : collection) {
            getSession().delete(entity);
        }
    }

    @Override
    public <T, C extends Collection<? extends T>> void update(C collection) {
        for (T entity : collection) {
            getSession().update(entity);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
