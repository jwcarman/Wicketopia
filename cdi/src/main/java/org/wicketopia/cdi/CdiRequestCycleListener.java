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

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.IPageClassRequestHandler;
import org.apache.wicket.request.handler.IPageRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketopia.cdi.spi.CdiFrameworkAdapter;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

public class CdiRequestCycleListener extends AbstractRequestCycleListener
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String CID_PARAM = "cid";

    private static final Logger logger = LoggerFactory.getLogger(CdiRequestCycleListener.class);

    private static final MetaDataKey<Boolean> CONVERSATION_STARTED_KEY = new MetaDataKey<Boolean>()
    {
    };

    private static final MetaDataKey<String> CID_KEY = new MetaDataKey<String>()
    {
    };

    @Inject
    private Conversation conversation;


    private final CdiFrameworkAdapter adapter;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CdiRequestCycleListener(CdiFrameworkAdapter adapter)
    {
        this.adapter = adapter;
    }

//----------------------------------------------------------------------------------------------------------------------
// IRequestCycleListener Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void onDetach(RequestCycle cycle)
    {
        if (managingConversationFor(cycle))
        {
            if (logger.isDebugEnabled())
            {
                if (conversation.isTransient())
                {
                    logger.debug("Abandoning transient conversation...");
                }
                else
                {
                    logger.debug("Suspending non-transient conversation {}...", conversation.getId());
                }

            }
            adapter.suspendConversation();
            cycle.setMetaData(CONVERSATION_STARTED_KEY, null);
        }
    }

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex)
    {
        adapter.suspendConversation();
        activateConversation(cycle, null);
        return null;
    }

    @Override
    public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler)
    {
        if (requiresPropagation(cycle))
        {
            saveConversationToPage(handler);
        }
    }

    @Override
    public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler)
    {
        if (managingConversationFor(cycle))
        {
            return;
        }
        String cid = cycle.getRequest().getRequestParameters().getParameterValue(CID_PARAM).toString();
        if (cid == null)
        {
            final Page page = getPage(handler);
            if (page != null)
            {
                cid = page.getMetaData(CID_KEY);
            }
        }
        activateConversation(cycle, cid);
    }

    @Override
    public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler)
    {
        if (requiresPropagation(cycle))
        {
            saveConversationToPage(handler);
            saveConversationToParameters(handler);
        }
    }

    @Override
    public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url)
    {
        if (requiresPropagation(cycle))
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("Rewriting url {} to append conversation id {}...", url.toAbsoluteString(), conversation.getId());
            }
            url.setQueryParameter(CID_PARAM, conversation.getId());
        }
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private void activateConversation(RequestCycle requestCycle, String cid)
    {
        if (!managingConversationFor(requestCycle))
        {
            if (logger.isDebugEnabled())
            {
                if (StringUtils.isEmpty(cid))
                {
                    logger.debug("Initiating transient conversation...");
                }
                else
                {
                    logger.debug("Resuming conversation {}...", cid);
                }
            }
            adapter.resumeConversation(cid);
            requestCycle.setMetaData(CONVERSATION_STARTED_KEY, true);
        }
    }

    private boolean managingConversationFor(RequestCycle requestCycle)
    {
        return Boolean.TRUE.equals(requestCycle.getMetaData(CONVERSATION_STARTED_KEY));
    }

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

    protected PageParameters getPageParameters(IRequestHandler handler)
    {
        if (handler instanceof IPageClassRequestHandler)
        {
            IPageClassRequestHandler pageHandler = (IPageClassRequestHandler) handler;
            return pageHandler.getPageParameters();
        }
        return null;
    }

    private boolean requiresPropagation(RequestCycle cycle)
    {
        return managingConversationFor(cycle) && !conversation.isTransient();
    }

    private void saveConversationToPage(IRequestHandler handler)
    {
        Page page = getPage(handler);
        if (page != null)
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("Saving non-transient conversation {} to current page's metadata...", conversation.getId());
            }
            page.setMetaData(CID_KEY, conversation.getId());
        }
    }

    private void saveConversationToParameters(IRequestHandler handler)
    {
        PageParameters parameters = getPageParameters(handler);
        if (parameters != null)
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("Saving non-transient conversation {} to page parameters...", conversation.getId());
            }
            parameters.set(CID_PARAM, conversation.getId());
        }
    }
}
