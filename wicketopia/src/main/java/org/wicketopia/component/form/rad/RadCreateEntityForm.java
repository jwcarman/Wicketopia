package org.wicketopia.component.form.rad;

import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.wicketopia.component.form.CreateEntityForm;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.metadata.BeanMetadata;

import java.io.Serializable;

/**
 * @author James Carman
 */
public abstract class RadCreateEntityForm<EntityType extends Entity<IdType>, IdType extends Serializable>
        extends CreateEntityForm<EntityType, IdType>
{
    private static final long serialVersionUID = 1L;
    private final PropertyEditorFactory propertyEditorFactory;

    public RadCreateEntityForm( String id, final PropertyEditorFactory propertyEditorFactory,
                                BeanMetadata<EntityType> beanMetadata, Repository<EntityType, IdType> repository )
    {
        super(id, repository);
        this.propertyEditorFactory = propertyEditorFactory;

    }

}
