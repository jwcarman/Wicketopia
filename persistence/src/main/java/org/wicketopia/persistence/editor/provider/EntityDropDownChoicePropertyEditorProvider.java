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

package org.wicketopia.persistence.editor.provider;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.wicketopia.context.Context;
import org.wicketopia.editor.provider.AbstractDropDownChoicePropertyEditorProvider;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.PersistenceUtils;

import java.util.List;

public class EntityDropDownChoicePropertyEditorProvider extends AbstractDropDownChoicePropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected <T> IModel<? extends List<? extends T>> createChoicesModel(Class<T> type, Context context)
    {
        PersistenceProvider provider = PersistenceUtils.getProvider(context);
        return new AllEntitiesModel<T>(provider, type);
    }

    @Override
    protected <T> IChoiceRenderer<T> createRenderer(DropDownChoice<T> ddc, Class<T> type, Context context)
    {
        final PersistenceProvider provider = PersistenceUtils.getProvider(context);
        return new EntityChoiceRenderer<T>(provider);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static class AllEntitiesModel<T> extends LoadableDetachableModel<List<T>>
    {
        private final PersistenceProvider persistenceProvider;
        private final Class<T> entityType;

        private AllEntitiesModel(PersistenceProvider persistenceProvider, Class<T> entityType)
        {
            this.persistenceProvider = persistenceProvider;
            this.entityType = entityType;
        }

        @Override
        protected List<T> load()
        {
            return persistenceProvider.getAll(entityType);
        }
    }

    private static class EntityChoiceRenderer<T> implements IChoiceRenderer<T>
    {
        private final PersistenceProvider persistenceProvider;

        private EntityChoiceRenderer(PersistenceProvider persistenceProvider)
        {
            this.persistenceProvider = persistenceProvider;
        }

        @Override
        public Object getDisplayValue(T object)
        {
            return String.valueOf(object);
        }

        @Override
        public String getIdValue(T object, int index)
        {
            return String.valueOf(persistenceProvider.getIdentifier(object));
        }
    }
}
