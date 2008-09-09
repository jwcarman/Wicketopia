package org.wicketopia.example.web.page;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.component.form.rad.RadCreateEntityForm;
import org.wicketopia.component.link.RemoveEntityLink;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;
import org.wicketopia.model.column.FragmentColumn;
import org.wicketopia.model.repeater.PageableRepositoryDataProvider;

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

    @SpringBean
    private PropertyEditorFactory propertyEditorFactory;

    public HomePage()
    {
        List<IColumn<?>> columns = new ArrayList<IColumn<?>>();
        columns.add(new PropertyColumn<String>(new Model<String>("Name"), "name", "name"));
        columns.add(new PropertyColumn<String>(new Model<String>("Description"), "description", "description"));
        columns.add(new PropertyColumn<String>(new Model<String>("Type"), "widgetType"));
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

        final RadCreateEntityForm<Widget, String> form =
                new RadCreateEntityForm<Widget, String>("form", propertyEditorFactory, widgetRepository)
                {
                    private static final long serialVersionUID = 1L;

                    protected void afterCreate( Widget entity )
                    {
                        setRedirect(true);
                        setResponsePage(HomePage.class);
                    }
                };
        add(new FeedbackPanel("feedback"));
        add(form);
    }
}
