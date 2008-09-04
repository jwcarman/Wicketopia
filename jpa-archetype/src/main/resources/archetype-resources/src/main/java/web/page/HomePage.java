package ${package}.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.model.Model;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wicketopia.model.LoadableDetachableEntityModel;
import org.wicketopia.model.repeater.PageableRepositoryDataProvider;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import ${package}.domain.repository.WidgetRepository;
import ${package}.domain.entity.Widget;

/**
 * Homepage
 */
public class HomePage extends BasePage
{

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WidgetRepository widgetRepository;
    
    public HomePage()
    {
        List<IColumn<?>> columns = new ArrayList<IColumn<?>>();
        columns.add(new PropertyColumn<String>(new Model<String>("Name"), "name", "name"));
        add(new AjaxFallbackDefaultDataTable<Widget>("table", columns, new PageableRepositoryDataProvider<Widget,String>(widgetRepository, "name"), 20));
    }
}
