package org.wicketopia.component.form.rad;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.wicketopia.component.form.CreateEntityForm;
import org.wicketopia.metadata.BeanMetadata;

import java.io.Serializable;

/**
 * @since 1.0
 */
public abstract class RadUpdateEntityForm<EntityType extends Entity<IdType>, IdType extends Serializable> extends
        CreateEntityForm<EntityType, IdType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final BeanMetadata<EntityType> beanMetadata;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public RadUpdateEntityForm( String id, BeanMetadata<EntityType> beanMetadata,
                                Repository<EntityType, IdType> repository )
    {
        super(id, repository);
        this.beanMetadata = beanMetadata;
    }
}
