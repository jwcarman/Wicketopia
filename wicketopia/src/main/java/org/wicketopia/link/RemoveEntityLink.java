package org.wicketopia.link;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * @author James Carman
 */
public class RemoveEntityLink<EntityType extends Entity<IdType>,IdType extends Serializable> extends Link<EntityType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Repository<EntityType,IdType> repository;
    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public RemoveEntityLink( String id, Repository<EntityType, IdType> repository, IModel<EntityType> model )
    {
        super(id, model);
        this.repository = repository;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    protected void afterRemove(EntityType entity)
    {
        // Do nothing!
    }

    public final void onClick()
    {
        final EntityType entity = getModelObject();
        repository.remove(entity);
        afterRemove(entity);
    }
}
