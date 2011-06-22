package org.wicketopia.example.web.page.list;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.list.BeanListLayoutPanel;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.model.list.EntityListModel;

import java.text.Normalizer;

public class BeanListEditorExample extends BasePage
{
    @SpringBean
    private PersistenceProvider persistenceProvider;

    public BeanListEditorExample()
    {
        final PropertyComponentFactory<Person> factory = Wicketopia.get().createEditorFactory(Person.class);
        Form form = new Form<Void>("form");
        final BeanListLayoutPanel<Person> list = new BeanListLayoutPanel<Person>("list", Person.class, new EntityListModel<Person>(Person.class, persistenceProvider), new Context(Context.UPDATE), factory);
        list.setOutputMarkupPlaceholderTag(true);
        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);
        form.add(new AjaxSubmitLink("submit")
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form)
            {
                persistenceProvider.update(list.getList());
                info("Persons updated successfully.");
                target.addComponent(feedback);
                target.addComponent(list);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form)
            {
                target.addComponent(feedback);
            }
        });

        form.add(list);
        add(form);
    }
}
