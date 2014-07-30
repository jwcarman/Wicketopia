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

package org.wicketopia.example.web.page.list;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.list.BeanListLayoutPanel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.model.list.EntityListModel;

public class BeanListViewerExample extends BasePage {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @SpringBean
    private PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanListViewerExample() {
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createViewerFactory(Person.class);
        final Context context = new Context(Context.LIST);
        final EntityListModel<Person> listModel = new EntityListModel<Person>(Person.class, persistenceProvider);
        add(new BeanListLayoutPanel<Person>("list", Person.class, listModel, context, factory));
    }
}
