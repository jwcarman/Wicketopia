/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.example.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.domdrides.model.repeater.PageableRepositoryDataProvider;
import org.wicketopia.editor.component.bean.VerticalListBeanEditor;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;
import org.wicketopia.model.column.FragmentColumn;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.component.link.ajax.AjaxCreateLink;
import org.wicketopia.persistence.component.link.ajax.AjaxDeleteLink;

import java.util.ArrayList;
import java.util.List;

/**
 * Homepage
 */
public class HomePage extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WidgetRepository widgetRepository;
    private final FeedbackPanel feedback;

    @SpringBean
    private PersistenceProvider persistenceProvider;
    private final AjaxFallbackDefaultDataTable<Widget> table;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public HomePage()
    {
        List<IColumn<Widget>> columns = new ArrayList<IColumn<Widget>>();
        columns.add(new PropertyColumn<Widget>(new Model<String>("Name"), "name", "name"));
        columns.add(new PropertyColumn<Widget>(new Model<String>("Description"), "description", "description"));
        columns.add(new PropertyColumn<Widget>(new Model<String>("Type"), "widgetType", "widgetType"));
        columns.add(new FragmentColumn<Widget>(new Model<String>("Actions"))
        {
            private static final long serialVersionUID = 1L;

            protected Fragment createFragment(String componentId, IModel<Widget> model)
            {
                Fragment f = new Fragment(componentId, "linksPanel", HomePage.this);
                f.add(new AjaxDeleteLink<Widget>("removeLink", model, persistenceProvider)
                {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void afterDelete(Widget object, AjaxRequestTarget target)
                    {
                        target.addComponent(table);
                    }
                });
                return f;
            }
        });
        table = new AjaxFallbackDefaultDataTable<Widget>("table", columns,
                new PageableRepositoryDataProvider<Widget, String>(
                        widgetRepository, "name"), 20);
        add(table);
        feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        final IModel<Widget> model = new Model<Widget>(new Widget());
        final Form<Widget> widgetForm = new Form<Widget>("form", model);
        widgetForm.add(new AjaxCreateLink<Widget>("submit", widgetForm, persistenceProvider)
        {
            @Override
            protected void afterCreate(Widget object, AjaxRequestTarget target)
            {
                target.addComponent(table);
                target.addComponent(widgetForm);
                target.addComponent(feedback);
                model.setObject(new Widget());
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
                target.addComponent(feedback);
            }
        });
        final EditorContext context = new EditorContext("CREATE");
        widgetForm.add(new VerticalListBeanEditor<Widget>("editor", Widget.class, model, context, "id", "version"));
        add(feedback);
        add(widgetForm);
    }
}
