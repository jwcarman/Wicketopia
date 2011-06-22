package org.wicketopia.example.web.page.bean;

import org.apache.wicket.model.Model;
import org.joda.time.LocalDate;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;

public class BeanViewerExample extends BasePage
{
    public BeanViewerExample()
    {
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createViewerFactory(Person.class);
        Person p = new Person();
        p.setFirstName("Test");
        p.setLastName("User");
        p.setEmail("test@user.com");
        p.setDob(new LocalDate());
        add(new CssBeanViewLayoutPanel<Person>("bean", Person.class, new Model<Person>(p), new Context(Context.VIEW), factory));
    }
}
