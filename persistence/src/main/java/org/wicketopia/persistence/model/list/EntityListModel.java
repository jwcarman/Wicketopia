package org.wicketopia.persistence.model.list;

import org.apache.wicket.model.LoadableDetachableModel;
import org.wicketopia.persistence.PersistenceProvider;

import java.util.List;

public class EntityListModel<T> extends LoadableDetachableModel<List<? extends T>>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Class<T> entityType;
    private final PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public EntityListModel(Class<T> entityType, PersistenceProvider persistenceProvider)
    {
        this.entityType = entityType;
        this.persistenceProvider = persistenceProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected List<? extends T> load()
    {
        return persistenceProvider.getAll(entityType);
    }
}
