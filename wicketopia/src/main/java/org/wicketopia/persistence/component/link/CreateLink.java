package org.wicketopia.persistence.component.link;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceProvider;


public abstract class CreateLink<T> extends SubmitLink
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceProvider persistenceProvider;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public CreateLink(String id, IModel<T> model, PersistenceProvider persistenceProvider)
    {
        super(id, model);
        this.persistenceProvider = persistenceProvider;
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
        object = persistenceProvider.create(object);
        afterCreate(object);
    }
}
