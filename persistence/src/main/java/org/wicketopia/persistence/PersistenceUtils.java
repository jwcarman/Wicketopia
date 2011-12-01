/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.persistence;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.WicketRuntimeException;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;

public class PersistenceUtils
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final MetaDataKey<PersistenceProvider> PERSISTENCE_PROVIDER_KEY = new MetaDataKey<PersistenceProvider>()
    {
    };

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static PersistenceProvider getProvider(Context context)
    {
        PersistenceProvider provider = context.getAttribute(PERSISTENCE_PROVIDER_KEY);
        if (provider == null)
        {
            PersistencePlugin persistencePlugin = Wicketopia.get().getPlugin(PersistencePlugin.class);
            provider = persistencePlugin.getDefaultProvider();
        }
        if (provider == null)
        {
            throw new WicketRuntimeException("No PersistenceProvider found!  Either set up a default persistence provider in the PersistencePlugin or use PersistenceUtils.setProvider() to add one to the context.");
        }
        return provider;
    }

    public static void setProvider(Context context, PersistenceProvider persistenceProvider)
    {
        context.setAttribute(PERSISTENCE_PROVIDER_KEY, persistenceProvider);
    }
}
