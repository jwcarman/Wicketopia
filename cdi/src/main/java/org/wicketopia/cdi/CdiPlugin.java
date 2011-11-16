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

import javax.enterprise.inject.spi.BeanManager;

/**
 * The CdiPlugin will automatically inject your application, components, behaviors, and sessions.  Since no
 * consistently-reliable bootstrap mechanism exists to obtain the {@link BeanManager}, this plugin will not
 * automatically register itself and requires
 * {@link Wicketopia#addPlugin(org.wicketopia.WicketopiaPlugin) manual registration}.
 *
 * @since 1.0
 */
public class CdiPlugin implements WicketopiaPlugin
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final BeanManager beanManager;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CdiPlugin(BeanManager beanManager)
    {
        this.beanManager = beanManager;
    }

//----------------------------------------------------------------------------------------------------------------------
// WicketopiaPlugin Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void initialize(Wicketopia wicketopia)
    {
        CdiUtils.inject(wicketopia.getApplication(), beanManager);
        wicketopia.getApplication().getRequestCycleListeners().add(new CdiRequestCycleListener());
        CdiInjector injector = new CdiInjector(beanManager);
        wicketopia.getApplication().getComponentInstantiationListeners().add(injector);
        wicketopia.getApplication().getBehaviorInstantiationListeners().add(injector);
        wicketopia.getApplication().getSessionListeners().add(injector);
    }
}
