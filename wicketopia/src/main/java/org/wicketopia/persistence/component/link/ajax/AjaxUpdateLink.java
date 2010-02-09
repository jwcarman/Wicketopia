package org.wicketopia.persistence.component.link.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.wicketopia.persistence.PersistenceProvider;

public abstract class AjaxUpdateLink<T> extends AjaxSubmitLink
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceProvider persistenceProvider;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    protected AjaxUpdateLink(String id, Form<T> form, PersistenceProvider persistenceProvider)
    {
        super(id, form);
        this.persistenceProvider = persistenceProvider;
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract void afterUpdate(T object, AjaxRequestTarget target);

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    @SuppressWarnings("unchecked")
    protected final void onSubmit(AjaxRequestTarget target, Form<?> form)
    {
        T object = (T)form.getModelObject();
        object = persistenceProvider.update(object);
        afterUpdate(object, target);
    }
}
