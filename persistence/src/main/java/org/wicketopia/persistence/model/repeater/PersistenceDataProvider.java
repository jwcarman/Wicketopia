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
public class PersistenceDataProvider<T> extends SortableDataProvider<T>
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

    public Iterator<? extends T> iterator(int first, int max)
    {
        final SortParam sort = getSort();
        return persistenceProvider.getList(beanType, first, max, sort == null ? null : sort.getProperty(), sort == null || sort.isAscending()).iterator();
    }

    public IModel<T> model(T entity)
    {
        return new LoadableDetachableEntityModel<T>(beanType, entity, persistenceProvider);
    }

    public int size()
    {
        return persistenceProvider.getCount(beanType);
    }
}
