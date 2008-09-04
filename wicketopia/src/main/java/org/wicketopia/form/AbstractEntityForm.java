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

package org.wicketopia.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;

import java.io.Serializable;

public abstract class AbstractEntityForm<EntityType extends Entity<IdType>,IdType extends Serializable> extends Form<EntityType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    protected final Repository<EntityType,IdType> repository;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    protected AbstractEntityForm( String id, Repository<EntityType, IdType> repository )
    {
        super(id);
        this.repository = repository;
    }

    protected AbstractEntityForm( String id, IModel<EntityType> entityTypeIModel, Repository<EntityType, IdType> repository )
    {
        super(id, entityTypeIModel);
        this.repository = repository;
    }

    protected abstract void onSubmit();
}
