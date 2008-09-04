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
import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.wicketopia.model.LoadableDetachableEntityModel;

import java.io.Serializable;

public class UpdateEntityForm<EntityType extends Entity<IdType>, IdType extends Serializable>
        extends AbstractEntityForm<EntityType, IdType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public UpdateEntityForm( String id, Repository<EntityType, IdType> repository, EntityType entity )
    {
        super(id, repository, new CompoundPropertyModel<EntityType>(
                new LoadableDetachableEntityModel<EntityType, IdType>(repository, entity)));
    }

    public UpdateEntityForm( String id, Repository<EntityType, IdType> repository, IdType entityId )
    {
        super(id, repository, new CompoundPropertyModel<EntityType>(
                new LoadableDetachableEntityModel<EntityType, IdType>(repository, entityId)));
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    protected final void onSubmit()
    {
        final EntityType entity = getModelObject();
        repository.update(entity);
        afterUpdate(entity);
    }

    /**
     * Subclasses can override this to provide behavior after the entity is updated (like redirecting to another page,
     * perhaps).
     *
     * @param entity the entity that was updated
     */
    protected void afterUpdate( EntityType entity )
    {
        // Do nothing!
    }
}
