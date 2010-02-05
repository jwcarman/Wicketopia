package org.wicketopia.domdrides;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.wicketopia.persistence.PersistenceService;

import java.io.Serializable;

public class DomdridesPersistenceService<EntityType extends Entity<IdType>, IdType extends Serializable> implements PersistenceService<EntityType>, Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Repository<EntityType,IdType> repository;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public DomdridesPersistenceService(Repository<EntityType, IdType> repository)
    {
        this.repository = repository;
    }

//**********************************************************************************************************************
// PersistenceService Implementation
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
