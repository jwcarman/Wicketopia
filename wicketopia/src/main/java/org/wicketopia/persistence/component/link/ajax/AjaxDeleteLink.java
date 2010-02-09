package org.wicketopia.persistence.component.link.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceProvider;

public abstract class AjaxDeleteLink<T> extends AjaxLink<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceProvider persistenceProvider;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public AjaxDeleteLink(String id, IModel<T> model, PersistenceProvider persistenceProvider)
    {
        super(id, model);
        this.persistenceProvider = persistenceProvider;
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
        persistenceProvider.delete(object);
        afterDelete(object, target);
    }
}
