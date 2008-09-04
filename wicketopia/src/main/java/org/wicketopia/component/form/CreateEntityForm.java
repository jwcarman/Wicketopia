/*
 * Copyright (c) 2008, Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.component.form;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;

import java.io.Serializable;

public abstract class CreateEntityForm<EntityType extends Entity<IdType>, IdType extends Serializable>
        extends AbstractEntityForm<EntityType, IdType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final Class<EntityType> entityType;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public CreateEntityForm( String id, Class<EntityType> entityType, Repository<EntityType, IdType> repository )
    {
        super(id, repository);
        this.entityType = entityType;
        setModel(new CompoundPropertyModel<EntityType>(new PrototypeModel()));
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    /**
     * Subclasses can override this method to provide a "clean" instance which the form populate and add to the
     * repository.
     * @return a "clean" instance which the form will populate and add to the repository
     */
    protected EntityType createPrototype()
    {
        try
        {
            return entityType.newInstance();
        }
        catch( IllegalAccessException e )
        {
            throw new RuntimeException("Unable to construct " + entityType.getName() + " object.", e);
        }
        catch( InstantiationException e )
        {
            throw new RuntimeException("Unable to construct " + entityType.getName() + " object.", e);
        }
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    protected final void onSubmit()
    {
        final EntityType entity = getModelObject();
        repository.add(entity);
        afterCreate(entity);
    }

    /**
     * Subclasses can override this to provide behavior after the entity has been created (like redirecting to another page,
     * perhaps).
     * @param entity the entity that was created (and saved to the database)
     */
    protected void afterCreate( EntityType entity )
    {
        // Do nothing!
    }

//**********************************************************************************************************************
// Inner Classes
//**********************************************************************************************************************

    /**
     * A prototype model merely returns whatever the {@link CreateEntityForm#createPrototype()}
     * method returns inside a {@link LoadableDetachableModel}.
     */
    private class PrototypeModel extends LoadableDetachableModel<EntityType>
    {
        private static final long serialVersionUID = 1L;

        protected EntityType load()
        {
            return createPrototype();
        }
    }
}
