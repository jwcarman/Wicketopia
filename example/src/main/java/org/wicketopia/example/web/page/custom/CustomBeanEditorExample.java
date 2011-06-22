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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.component.link.ajax.AjaxCreateLink;

public class CustomBeanEditorExample extends BasePage
{
    @SpringBean
    private PersistenceProvider persistenceProvider;

    public CustomBeanEditorExample()
    {
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createEditorFactory(Person.class);
        final Model<Person> model = new Model<Person>(Person.createDummy());
        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupPlaceholderTag(true);
        final Form<Person> form = new Form<Person>("form", model);
        final Context context = new Context(Context.CREATE);
        addLabelAndEditor(form, factory, model, "firstName", context);
        addLabelAndEditor(form, factory, model, "lastName", context);
        form.add(new AjaxCreateLink<Person>("submit", form, persistenceProvider)
        {
            @Override
            protected void afterCreate(Person object, AjaxRequestTarget target)
            {
                model.setObject(Person.createDummy());
                info("Person " + object + " created.");
                target.addComponent(form);
                target.addComponent(feedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
                target.addComponent(feedback);
            }
        });
        add(form);
    }

    private void addLabelAndEditor(Form<Person> form, PropertyComponentFactory<Person> factory, Model<Person> model, String propertyName, Context context)
    {
        form.add(factory.createPropertyLabel(propertyName + "Label", propertyName));
        form.add(factory.createPropertyComponent(propertyName, model, propertyName, context));
    }
}
