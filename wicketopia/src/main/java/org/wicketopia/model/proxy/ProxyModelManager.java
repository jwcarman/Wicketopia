package org.wicketopia.model.proxy;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @since 1.0
 */
public class ProxyModelManager
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final List<ProxyModel<?>> proxyModels = new LinkedList<ProxyModel<?>>();

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public <T extends Serializable> IModel<T> proxy( IModel<T> actual )
    {
        final ProxyModel<T> model = new ProxyModel<T>(actual);
        proxyModels.add(model);
        return model;
    }

    public void commit()
    {
        for( ProxyModel<?> proxyModel : proxyModels )
        {
            proxyModel.commit();
        }
    }

//**********************************************************************************************************************
// Inner Classes
//**********************************************************************************************************************

    private static class ProxyModel<T extends Serializable> extends Model<T>
    {
        private final IModel<T> destination;
        private static final long serialVersionUID = 1L;

        private ProxyModel( IModel<T> destination )
        {
            this.destination = destination;
        }

        public void commit()
        {
            destination.setObject(getObject());
        }
    }
}
