package org.wicketopia.example.web.page.list;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.list.BeanListLayoutPanel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.model.list.EntityListModel;

public class BeanListViewerExample extends BasePage
{
    @SpringBean
    private PersistenceProvider persistenceProvider;

    public BeanListViewerExample()
    {
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createViewerFactory(Person.class);
        add(new BeanListLayoutPanel<Person>("list", Person.class, new EntityListModel<Person>(Person.class, persistenceProvider), new Context(Context.LIST), factory));
    }
}
