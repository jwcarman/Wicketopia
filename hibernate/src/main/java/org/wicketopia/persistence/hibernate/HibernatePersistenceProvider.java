/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.wicketopia.persistence.PersistenceProvider;


/**
 * @author James Carman
 */
public class HibernatePersistenceProvider implements PersistenceProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private SessionFactory sessionFactory;

//----------------------------------------------------------------------------------------------------------------------
// PersistenceProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public <T> T create(T object)
    {
        getSession().save(object);
        return object;
    }

    @Override
    public <T> void delete(T object)
    {
        getSession().delete(object);
    }

    @Override
    public <T> T update(T object)
    {
        getSession().update(object);
        return object;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
}
