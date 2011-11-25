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

import org.apache.wicket.Component;
import org.apache.wicket.IBehaviorInstantiationListener;
import org.apache.wicket.ISessionListener;
import org.apache.wicket.Session;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.Behavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.BeanManager;

public class CdiInjector implements IComponentInstantiationListener, IBehaviorInstantiationListener, ISessionListener
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final BeanManager beanManager;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CdiInjector(BeanManager beanManager)
    {
        this.beanManager = beanManager;
    }

//----------------------------------------------------------------------------------------------------------------------
// IBehaviorInstantiationListener Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void onInstantiation(Behavior behavior)
    {
        inject(behavior);
    }

//----------------------------------------------------------------------------------------------------------------------
// IComponentInstantiationListener Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void onInstantiation(Component component)
    {
        inject(component);
    }

//----------------------------------------------------------------------------------------------------------------------
// ISessionListener Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void onCreated(Session session)
    {
        inject(session);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private <T> void inject(T object)
    {
        CdiUtils.inject(object, beanManager);
        CdiUtils.postConstruct(object, beanManager);
    }
}

