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

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.jboss.weld.environment.servlet.Listener;
import org.wicketopia.cdi.spi.CdiFrameworkAdapter;

import javax.enterprise.inject.spi.BeanManager;

public class WeldAdapter implements CdiFrameworkAdapter
{
    @Override
    public BeanManager getBeanManager(WebApplication application)
    {
        return (BeanManager) application.getServletContext().getAttribute(Listener.BEAN_MANAGER_ATTRIBUTE_NAME);
    }

    @Override
    public void resumeConversation(String cid)
    {
    }
}
