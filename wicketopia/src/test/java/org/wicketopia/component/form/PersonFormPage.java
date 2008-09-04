package org.wicketopia.component.form;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public abstract class PersonFormPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public PersonFormPage()
    {
        Form form = createPersonForm();
        form.add(new TextField("first"));
        form.add(new TextField("last"));
        add(form);
    }
    
    protected abstract Form<Person> createPersonForm();
}
