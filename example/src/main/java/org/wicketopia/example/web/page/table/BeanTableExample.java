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

package org.wicketopia.example.web.page.table;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.model.repeater.PersistenceDataProvider;

import java.util.List;

public class BeanTableExample extends BasePage
{
    @SpringBean
    private PersistenceProvider persistenceProvider;

    public BeanTableExample()
    {
        final PersistenceDataProvider<Person> dataProvider = new PersistenceDataProvider<Person>(Person.class, persistenceProvider);
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createViewerFactory(Person.class);
        final Context context = new Context(Context.LIST);
        final List<IColumn<Person>> columns = Wicketopia.get().createColumns(Person.class, factory, context);
        add(new AjaxFallbackDefaultDataTable<Person>("table", columns, dataProvider, 25));
    }
}
