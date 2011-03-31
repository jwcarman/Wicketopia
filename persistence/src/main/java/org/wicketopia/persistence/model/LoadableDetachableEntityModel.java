package org.wicketopia.persistence.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.wicketopia.persistence.PersistenceProvider;

import java.io.Serializable;

/**
 * @author James Carman
 */
public class LoadableDetachableEntityModel<T> extends LoadableDetachableModel<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final PersistenceProvider persistenceProvider;
    private final Class<T> entityType;
    private final Serializable identifier;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public LoadableDetachableEntityModel(Class<T> entityType, Serializable identifier, PersistenceProvider persistenceProvider)
    {
        this.entityType = entityType;
        this.identifier = identifier;
        this.persistenceProvider = persistenceProvider;
    }

    public LoadableDetachableEntityModel(Class<T> entityType, T entity, PersistenceProvider persistenceProvider)
    {
        super(entity);
        this.identifier  = persistenceProvider.getIdentifier(entity);
        this.entityType = entityType;
        this.persistenceProvider = persistenceProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected T load()
    {
        return persistenceProvider.getByIdentifier(entityType, identifier);
    }
}
