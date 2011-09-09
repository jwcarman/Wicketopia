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

package org.wicketopia.example.web.page.bean;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.component.form.CreateEntityForm;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;

public class BeanEditorExample extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanEditorExample()
    {
        final Form<Person> form = new CreateEntityForm<Person>("form", Person.class);
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createEditorFactory(Person.class);
        final Context context = new Context(Context.CREATE);
        final IModel<Person> model = form.getModel();
        form.add(new CssBeanViewLayoutPanel<Person>("bean", Person.class, model, context, factory));
        add(form);
    }
}
