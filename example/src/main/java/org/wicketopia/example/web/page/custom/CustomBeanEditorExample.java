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

package org.wicketopia.example.web.page.custom;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.component.form.CreatePersonForm;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.persistence.PersistenceProvider;

public class CustomBeanEditorExample extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CustomBeanEditorExample()
    {
        final Form<Person> form = new CreatePersonForm("form");
        final IModel<Person> model = form.getModel();
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createEditorFactory(Person.class);
        final Context context = new Context(Context.CREATE);
        addLabelAndEditor(form, factory, model, "firstName", context);
        addLabelAndEditor(form, factory, model, "lastName", context);
        add(form);
    }

    private void addLabelAndEditor(Form<Person> form, PropertyComponentFactory<Person> factory, IModel<Person> model, String propertyName, Context context)
    {
        form.add(factory.createPropertyLabel(propertyName + "Label", propertyName));
        form.add(factory.createPropertyComponent(propertyName, model, propertyName, context));
    }
}