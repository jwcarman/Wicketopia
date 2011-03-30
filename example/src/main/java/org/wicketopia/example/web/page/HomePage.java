/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.example.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.context.Context;
import org.wicketopia.domdrides.component.link.ajax.AjaxCreateEntityLink;
import org.wicketopia.domdrides.model.repeater.PageableRepositoryDataProvider;
import org.wicketopia.example.domain.entity.Gadget;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.factory.PropertyEditorComponentFactory;
import org.wicketopia.layout.list.BeanListLayoutPanel;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;
import org.wicketopia.metadata.WicketopiaFacet;
import org.wicketopia.model.column.BeanPropertyColumn;

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


//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public HomePage()
    {
        feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupPlaceholderTag(true);
        final IModel<Widget> model = new Model<Widget>(new Widget());
        final PropertyComponentFactory<Widget> viewerFactory = WicketopiaPlugin.get().createViewerFactory(Widget.class);
        final List<IColumn<Widget>> columns = new ArrayList<IColumn<Widget>>();
        final Context viewContext = new Context(Context.LIST);
        columns.add(new BeanPropertyColumn<Widget>(viewerFactory, "name", viewContext));
        columns.add(new BeanPropertyColumn<Widget>(viewerFactory, "description", viewContext));
        columns.add(new BeanPropertyColumn<Widget>(viewerFactory, "widgetType", viewContext));
        final AjaxFallbackDefaultDataTable<Widget> table = new AjaxFallbackDefaultDataTable<Widget>("table", columns, new PageableRepositoryDataProvider<Widget, String>(widgetRepository, "name"), 20);
        add(table);

        final Form<Widget> widgetForm = new Form<Widget>("form", model);
        widgetForm.add(new AjaxCreateEntityLink<Widget, String>("submit", widgetRepository, model)
        {
            @Override
            protected void afterCreate(Widget object, AjaxRequestTarget target)
            {
                target.addComponent(widgetForm);
                target.addComponent(feedback);
                target.addComponent(table);
                model.setObject(new Widget());
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
                target.addComponent(feedback);
            }
        });



        Context context = new Context(Context.CREATE);
        widgetForm.add(new CssBeanViewLayoutPanel<Widget>("editor", Widget.class, model, context, WicketopiaPlugin.get().createEditorFactory(Widget.class)));
        add(feedback);
        add(widgetForm);



    }
}
