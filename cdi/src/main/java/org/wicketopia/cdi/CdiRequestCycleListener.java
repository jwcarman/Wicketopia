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

import org.apache.wicket.Page;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.IPageRequestHandler;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

public class CdiRequestCycleListener extends AbstractRequestCycleListener
{
    @Inject
    private Conversation conversation;


    @Override
    public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler)
    {

    }

    /**
     * Resolves a page instance from the request handler iff the page instance is already created
     *
     * @param handler
     * @return page or {@code null} if none
     */
    protected Page getPage(IRequestHandler handler)
    {
        if (handler instanceof IPageRequestHandler)
        {
            IPageRequestHandler pageHandler = (IPageRequestHandler) handler;
            if (pageHandler.isPageInstanceCreated())
            {
                return (Page) pageHandler.getPage();
            }
        }
        return null;
    }
}
