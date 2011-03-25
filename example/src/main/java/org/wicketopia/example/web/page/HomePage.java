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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.domdrides.component.link.ajax.AjaxCreateEntityLink;
import org.wicketopia.editor.component.bean.VerticalListBeanEditor;
import org.wicketopia.editor.component.list.BeanListEditor;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.example.domain.entity.Gadget;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;

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
        final Form<Widget> widgetForm = new Form<Widget>("form", model);
        widgetForm.add(new AjaxCreateEntityLink<Widget, String>("submit", widgetRepository, model)
        {
            @Override
            protected void afterCreate(Widget object, AjaxRequestTarget target)
            {
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

        EditorContext context = new EditorContext("CREATE");
        widgetForm.add(new VerticalListBeanEditor<Widget>("editor", Widget.class, model, context));
        widgetForm.add(new BeanListEditor<Gadget>("gadgets", Gadget.class, new PropertyModel<List<Gadget>>(model, "gadgets"), context, "name", "description").setOutputMarkupPlaceholderTag(true));
        widgetForm.add(new AjaxLink<Void>("addGadget")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                model.getObject().getGadgets().add(new Gadget());
                target.addComponent(widgetForm);
            }
        });
        add(feedback);
        add(widgetForm);
    }
}
