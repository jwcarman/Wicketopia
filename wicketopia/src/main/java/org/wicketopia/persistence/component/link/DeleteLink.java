package org.wicketopia.persistence.component.link;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceService;

public class DeleteLink<T> extends Link<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private PersistenceService persistenceService;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public DeleteLink(String id, IModel<T> model, PersistenceService persistenceService)
    {
        super(id, model);
        this.persistenceService = persistenceService;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    public final void onClick()
    {
        final T object = getModelObject();
        persistenceService.delete(object);
        afterDelete(object);
    }

    protected void afterDelete(T object)
    {

    }
}
