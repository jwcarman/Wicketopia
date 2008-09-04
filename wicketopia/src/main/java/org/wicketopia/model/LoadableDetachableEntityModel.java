package org.wicketopia.model;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.apache.wicket.model.LoadableDetachableModel;

import java.io.Serializable;

/**
 * An implementation of {@link org.apache.wicket.model.LoadableDetachableModel} which loads an entity from a {@link org.domdrides.repository.Repository}.
 *
 * @since 1.1
 */
public class LoadableDetachableEntityModel<EntityType extends Entity<IdType>, IdType extends Serializable> extends
        LoadableDetachableModel<EntityType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final Repository<EntityType,IdType> repository;
    private final IdType id;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    /**
     * Creates a model that lazily loads an entity by id from the specified repository.
     * @param repository the repository
     * @param id the id
     */
    public LoadableDetachableEntityModel(Repository<EntityType, IdType> repository, IdType id)
    {
        this.repository = repository;
        this.id = id;
    }

    /**
     * Creates a model pre-loaded with the specified entity.
     * @param repository the repository
     * @param entity the entity
     */
    public LoadableDetachableEntityModel(Repository<EntityType,IdType> repository, EntityType entity)
    {
       super(entity);
        this.repository = repository;
        this.id = entity.getId();
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    /**
     * Loads the entity from the repository.
     *
     * @return the entity
     */
    protected EntityType load()
    {
        return repository.getById(id);
    }
}
