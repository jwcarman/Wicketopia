package org.wicketopia.example.web.page;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.domdrides.component.link.RemoveEntityLink;
import org.wicketopia.domdrides.model.repeater.PageableRepositoryDataProvider;
import org.wicketopia.editor.BeanEditorHelper;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;
import org.wicketopia.model.column.FragmentColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * Homepage
 */
public class HomePage extends BasePage
{

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WidgetRepository widgetRepository;
    private final FeedbackPanel feedback;

    public HomePage()
    {
        List<IColumn<Widget>> columns = new ArrayList<IColumn<Widget>>();
        columns.add(new PropertyColumn<Widget>(new Model<String>("Name"), "name", "name"));
        columns.add(new PropertyColumn<Widget>(new Model<String>("Description"), "description", "description"));
        columns.add(new PropertyColumn<Widget>(new Model<String>("Type"), "widgetType"));
        columns.add(new FragmentColumn<Widget>(new Model<String>("Actions"))
        {
            private static final long serialVersionUID = 1L;

            protected Fragment createFragment( String componentId, IModel<Widget> model )
            {
                Fragment f = new Fragment(componentId, "linksPanel", HomePage.this);
                f.add(new RemoveEntityLink<Widget, String>("removeLink", widgetRepository, model)
                {
                    private static final long serialVersionUID = 1L;

                    protected void afterRemove( Widget entity )
                    {
                        setRedirect(true);
                        setResponsePage(HomePage.class);
                    }
                });
                return f;
            }
        });
        add(new AjaxFallbackDefaultDataTable<Widget>("table", columns,
                                                     new PageableRepositoryDataProvider<Widget, String>(
                                                             widgetRepository, "name"), 20));

        final IModel<Widget> model = new Model<Widget>(new Widget());
        final Form<Widget> widgetForm = new Form<Widget>("form", model);
        final BeanEditorHelper helper = new BeanEditorHelper<Widget>(Widget.class, model);
        widgetForm.add(helper.createEditorsView("editors", "id"));
        widgetForm.add(new SubmitLink("submit")
        {
            @Override
            public void onSubmit()
            {
                widgetRepository.add(model.getObject());
                widgetForm.setModelObject(new Widget());
            }
        });
        feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);
        add(widgetForm);
    }
}
