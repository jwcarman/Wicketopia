/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.example.web.component.form;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.metadata.WicketopiaBeanFacet;
import org.wicketopia.model.label.DisplayNameModel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.component.link.ajax.AjaxCreateLink;

import java.io.Serializable;

public class CreateEntityForm<T extends Serializable> extends Form<T> {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @SpringBean
    private PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CreateEntityForm(String id, final Class<T> entityType) {
        super(id);
        setModel(new Model<T>(createEntity(entityType)));
        add(new AjaxCreateLink<T>("submit", this, persistenceProvider) {
            @Override
            protected void afterCreate(final T object, AjaxRequestTarget target) {
                CreateEntityForm.this.setModelObject(createEntity(entityType));
                final WicketopiaBeanFacet facet = WicketopiaBeanFacet.get(Wicketopia.get().getBeanMetaData(entityType));
                info(DisplayNameModel.getDisplayName(facet, getLocalizer(), CreateEntityForm.this) + " created.");
                target.add(CreateEntityForm.this);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // TODO Auto-generated method stub: do nothing on form errors?
            }
        });
    }

    private static <T> T createEntity(Class<T> entityType) {
        try {
            return entityType.newInstance();
        } catch (InstantiationException e) {
            throw new WicketRuntimeException("Unable to instantate a " + entityType.getSimpleName() + " object.", e);
        } catch (IllegalAccessException e) {
            throw new WicketRuntimeException("Unable to instantate a " + entityType.getSimpleName() + " object.", e);
        }
    }
}
