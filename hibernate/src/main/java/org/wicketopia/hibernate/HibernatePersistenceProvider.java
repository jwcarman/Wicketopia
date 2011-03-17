/*
 * Copyright (c) 2010 Carman Consulting, Inc.
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

package org.wicketopia.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.wicketopia.persistence.PersistenceProvider;

/**
 * @author James Carman
 */
public class HibernatePersistenceProvider extends HibernateDaoSupport implements PersistenceProvider
{
//----------------------------------------------------------------------------------------------------------------------
// PersistenceProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public <T> T create(T object)
    {
        getSession().save(object);
        return object;
    }

    @Override
    @Transactional
    public <T> void delete(T object)
    {
        getSession().delete(object);
    }

    @Override
    @Transactional
    public <T> T update(T object)
    {
        getSession().update(object);
        return object;
    }
}
