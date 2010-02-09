package org.wicketopia.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.wicketopia.persistence.PersistenceProvider;

/**
 * @author James Carman
 */
public class HibernatePersistenceProvider extends HibernateDaoSupport implements PersistenceProvider
{
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
