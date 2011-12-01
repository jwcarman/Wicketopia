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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.list.BeanListLayoutPanel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.PersistenceUtils;
import org.wicketopia.persistence.model.list.EntityListModel;

import java.text.Normalizer;

public class BeanListEditorExample extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @SpringBean
    private PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BeanListEditorExample()
    {
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createEditorFactory(Person.class);
        Form form = new Form<Void>("form");
        final Context context = new Context(Context.UPDATE);
        final BeanListLayoutPanel<Person> list = new BeanListLayoutPanel<Person>("list", Person.class, new EntityListModel<Person>(Person.class, persistenceProvider), context, factory);
        list.setOutputMarkupPlaceholderTag(true);
        form.add(new AjaxSubmitLink("submit")
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form)
            {
                persistenceProvider.update(list.getList());
                info("Persons updated successfully.");
                target.add(list);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
                // Do nothing (using auto-feedback).
            }
        });

        form.add(list);
        add(form);
    }
}
