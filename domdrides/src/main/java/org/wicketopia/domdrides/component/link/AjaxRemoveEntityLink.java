/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.domdrides.component.link;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;

import java.io.Serializable;

/**
 * An ajax-enabled link that removes an entity.
 * @since 1.0
 */
public abstract class AjaxRemoveEntityLink<EntityType extends Entity<IdType>,IdType extends Serializable> extends AjaxLink<EntityType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final Repository<EntityType,IdType> repository;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public AjaxRemoveEntityLink( String id, Repository<EntityType, IdType> repository, IModel<EntityType> model )
    {
        super(id, model);
        this.repository = repository;
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    /**
     * Subclasses must override this to provide behavior after the entity has been removed (like redirecting to another page,
     * perhaps).  Typically this would be used to update whatever component(s) displayed this link in the first place
     * (such as a table or list).
     *
     * @param entity the entity that was removed
     * @param ajaxRequestTarget the ajax request target
     */
    protected abstract void afterRemove(EntityType entity, AjaxRequestTarget ajaxRequestTarget);

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public final void onClick( AjaxRequestTarget ajaxRequestTarget )
    {
        final EntityType entity = getModelObject();
        repository.remove(entity);
        afterRemove(entity, ajaxRequestTarget);
    }
}
