package org.wicketopia.domdrides.component.link;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * @author James Carman
 */
public abstract class RemoveEntityLink<EntityType extends Entity<IdType>,IdType extends Serializable> extends Link<EntityType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final Repository<EntityType,IdType> repository;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public RemoveEntityLink( String id, Repository<EntityType, IdType> repository, IModel<EntityType> model )
    {
        super(id, model);
        this.repository = repository;
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    /**
     * Subclasses must override this to provide behavior after the entity has been removed (like redirecting to another page,
     * perhaps).  If you wish to remain on the same screen, just override with a no-op implementation.
     *
     * @param entity the entity that was removed
     */
    protected abstract void afterRemove(EntityType entity);

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public final void onClick()
    {
        final EntityType entity = getModelObject();
        repository.remove(entity);
        afterRemove(entity);
    }
}
