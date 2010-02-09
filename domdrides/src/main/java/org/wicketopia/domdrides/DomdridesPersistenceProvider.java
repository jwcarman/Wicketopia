package org.wicketopia.domdrides;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.wicketopia.persistence.PersistenceProvider;

import java.io.Serializable;

public class DomdridesPersistenceProvider<EntityType extends Entity<IdType>, IdType extends Serializable> implements PersistenceProvider<EntityType>, Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Repository<EntityType,IdType> repository;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public DomdridesPersistenceProvider(Repository<EntityType, IdType> repository)
    {
        this.repository = repository;
    }

//**********************************************************************************************************************
// PersistenceProvider Implementation
//**********************************************************************************************************************


    public EntityType create(EntityType object)
    {
        return repository.add(object);
    }

    public void delete(EntityType object)
    {
        repository.remove(object);
    }

    public EntityType update(EntityType object)
    {
        return repository.update(object);
    }
}
