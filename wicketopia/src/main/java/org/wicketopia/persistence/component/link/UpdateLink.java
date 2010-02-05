package org.wicketopia.persistence.component.link;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceService;

public abstract class UpdateLink<T> extends SubmitLink
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceService<T> persistenceService;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public UpdateLink(String id, IModel<T> model, PersistenceService<T> persistenceService)
    {
        super(id, model);
        this.persistenceService = persistenceService;
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract void afterUpdate(T object);

//**********************************************************************************************************************
// IFormSubmittingComponent Implementation
//**********************************************************************************************************************

    @Override
    public final void onSubmit()
    {
        T object = (T) getDefaultModelObject();
        object = persistenceService.update(object);
        afterUpdate(object);
    }
}
