/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;
    private final Repository<EntityType,IdType> repository;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public RemoveEntityLink( String id, Repository<EntityType, IdType> repository, IModel<EntityType> model )
    {
        super(id, model);
        this.repository = repository;
    }

//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Subclasses must override this to provide behavior after the entity has been removed (like redirecting to another page,
     * perhaps).  If you wish to remain on the same screen, just override with a no-op implementation.
     *
     * @param entity the entity that was removed
     */
    protected abstract void afterRemove(EntityType entity);

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public final void onClick()
    {
        final EntityType entity = getModelObject();
        repository.remove(entity);
        afterRemove(entity);
    }
}
