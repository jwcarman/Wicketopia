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

package org.wicketopia.cdi.weld;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.http.HttpConversationContext;
import org.jboss.weld.environment.servlet.Listener;
import org.jboss.weld.resources.ManagerObjectFactory;
import org.wicketopia.cdi.spi.CdiFrameworkAdapter;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class WeldAdapter implements CdiFrameworkAdapter
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @Inject
    HttpConversationContext context;

//----------------------------------------------------------------------------------------------------------------------
// CdiFrameworkAdapter Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public BeanManager getBeanManager(WebApplication application)
    {
        return (BeanManager) application.getServletContext().getAttribute(Listener.BEAN_MANAGER_ATTRIBUTE_NAME);
    }

    @Override
    public void beginTransientConversation()
    {
        context.activate();
    }

    @Override
    public void resumeConversation(String cid)
    {
        if (StringUtils.isEmpty(cid))
        {
            throw new IllegalArgumentException("Non-transient conversation id '" + cid + "' is invalid.");
        }
        context.associate(getHttpServletRequest());
        context.activate(cid);
    }

    @Override
    public void suspendConversation()
    {
        context.deactivate();
        context.dissociate(getHttpServletRequest());
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private HttpServletRequest getHttpServletRequest()
    {
        return (HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
    }
}
