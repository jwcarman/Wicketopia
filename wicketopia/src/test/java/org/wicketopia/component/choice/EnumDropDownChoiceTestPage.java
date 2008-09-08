package org.wicketopia.component.choice;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketopia.util.Gender;
import org.wicketopia.util.Person;

/**
 * @since 1.0
 */
public class EnumDropDownChoiceTestPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public EnumDropDownChoiceTestPage(Person p)
    {
        Form<Person> form = new Form<Person>("form", new CompoundPropertyModel<Person>(p));
        form.add(new EnumDropDownChoice<Gender>("gender", Gender.class));
        add(form);
    }
}
