package org.wicketopia.persistence.component.link;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceService;


public abstract class CreateLink<T> extends SubmitLink
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceService<T> persistenceService;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public CreateLink(String id, IModel<T> model, PersistenceService<T> persistenceService)
    {
        super(id, model);
        this.persistenceService = persistenceService;
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract void afterCreate(T object);

//**********************************************************************************************************************
// IFormSubmittingComponent Implementation
//**********************************************************************************************************************

    @Override
    @SuppressWarnings("unchecked")
    public final void onSubmit()
    {
        T object = (T) getDefaultModelObject();
        object = persistenceService.create(object);
        afterCreate(object);
    }
}
