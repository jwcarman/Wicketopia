package org.wicketopia.persistence.component.link.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceService;

public abstract class AjaxDeleteLink<T> extends AjaxLink<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceService<T> persistenceService;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public AjaxDeleteLink(String id, IModel<T> model, PersistenceService<T> persistenceService)
    {
        super(id, model);
        this.persistenceService = persistenceService;
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract void afterDelete(T object, AjaxRequestTarget target);

//**********************************************************************************************************************
// IAjaxLink Implementation
//**********************************************************************************************************************

    @Override
    public final void onClick(AjaxRequestTarget target)
    {
        final T object = getModelObject();
        persistenceService.delete(object);
        afterDelete(object, target);
    }
}
