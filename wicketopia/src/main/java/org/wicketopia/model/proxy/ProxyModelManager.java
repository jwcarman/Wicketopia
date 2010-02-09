/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.model.proxy;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @since 1.0
 */
public class ProxyModelManager implements Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final List<ProxyModel<?>> proxyModels = new LinkedList<ProxyModel<?>>();

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public void commit()
    {
        for( ProxyModel<?> proxyModel : proxyModels )
        {
            proxyModel.commit();
        }
    }

    public <T extends Serializable> IModel<T> proxy( IModel<T> actual )
    {
        final ProxyModel<T> model = new ProxyModel<T>(actual);
        proxyModels.add(model);
        return model;
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
            setObject(destination.getObject());
        }

        public void commit()
        {
            destination.setObject(getObject());
        }
    }
}
