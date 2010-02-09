package org.wicketopia.persistence.component.link.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.wicketopia.persistence.PersistenceService;

public abstract class AjaxUpdateLink<T> extends AjaxSubmitLink
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceService persistenceService;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    protected AjaxUpdateLink(String id, Form<T> form, PersistenceService persistenceService)
    {
        super(id, form);
        this.persistenceService = persistenceService;
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
        object = persistenceService.update(object);
        afterUpdate(object, target);
    }
}
