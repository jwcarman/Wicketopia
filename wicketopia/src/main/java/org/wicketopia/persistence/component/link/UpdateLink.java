package org.wicketopia.persistence.component.link;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.wicketopia.persistence.PersistenceProvider;

public abstract class UpdateLink<T> extends SubmitLink
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final PersistenceProvider persistenceProvider;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public UpdateLink(String id, IModel<T> model, PersistenceProvider persistenceProvider)
    {
        super(id, model);
        this.persistenceProvider = persistenceProvider;
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
        object = persistenceProvider.update(object);
        afterUpdate(object);
    }
}
