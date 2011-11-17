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

package org.wicketopia.cdi;

import org.wicketopia.Wicketopia;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.cdi.spi.CdiFrameworkAdapter;
import org.wicketopia.util.ServiceLocator;

import javax.enterprise.inject.spi.BeanManager;
import java.util.ServiceLoader;

/**
 * The CdiPlugin will automatically inject your application, components, behaviors, and sessions.
 *
 * @since 1.0
 */
public class CdiPlugin implements WicketopiaPlugin
{
    private CdiFrameworkAdapter adapter;

//----------------------------------------------------------------------------------------------------------------------
// WicketopiaPlugin Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void initialize(Wicketopia wicketopia)
    {
        this.adapter = ServiceLocator.find(CdiFrameworkAdapter.class);
        final BeanManager beanManager = adapter.getBeanManager(wicketopia.getApplication());
        CdiUtils.inject(wicketopia.getApplication(), beanManager);
        final CdiRequestCycleListener requestCycleListener = new CdiRequestCycleListener();
        CdiUtils.inject(requestCycleListener, beanManager);
        wicketopia.getApplication().getRequestCycleListeners().add(requestCycleListener);
        CdiInjector injector = new CdiInjector(beanManager);
        wicketopia.getApplication().getComponentInstantiationListeners().add(injector);
        wicketopia.getApplication().getBehaviorInstantiationListeners().add(injector);
        wicketopia.getApplication().getSessionListeners().add(injector);
    }
}
