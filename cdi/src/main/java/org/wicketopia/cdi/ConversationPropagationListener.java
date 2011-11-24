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

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.IPageClassRequestHandler;
import org.apache.wicket.request.handler.IPageRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketopia.cdi.spi.CdiFrameworkAdapter;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

public class ConversationPropagationListener extends AbstractRequestCycleListener
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String CID_PARAM = "cid";

    private static final Logger logger = LoggerFactory.getLogger(ConversationPropagationListener.class);

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

    public ConversationPropagationListener(CdiFrameworkAdapter adapter)
    {
        this.adapter = adapter;
    }

//----------------------------------------------------------------------------------------------------------------------
// IRequestCycleListener Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void onBeginRequest(RequestCycle cycle)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("***** New Request Cycle *****");
        }
    }

    @Override
    public void onDetach(RequestCycle cycle)
    {
        if (conversationStarted(cycle))
        {
            if (logger.isTraceEnabled())
            {
                if (conversation.isTransient())
                {
                    logger.trace("Abandoning transient conversation...");
                }
                else
                {
                    logger.trace("Suspending non-transient conversation {}...", conversation.getId());
                }

            }
            adapter.suspendConversation();
            cycle.setMetaData(CONVERSATION_STARTED_KEY, null);
        }
    }

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex)
    {
        //adapter.suspendConversation();
        return null;
    }

    @Override
    public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("Request handler {} executed.", handler.getClass().getSimpleName());
        }
        propagateToPageMetaData(handler);
        propagateToPageParameters(handler);

    }

    @Override
    public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("Request handler {} resolved.", handler.getClass().getSimpleName());
        }
        if (conversationStarted(cycle))
        {
            return;
        }
        if (logger.isTraceEnabled())
        {
            logger.trace("Checking request parameters for conversation id...");
        }
        String cid = cycle.getRequest().getRequestParameters().getParameterValue(CID_PARAM).toString();
        if (cid == null)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Checking page metadata for conversation id...");
            }
            final Page page = getPage(handler);
            if (page != null)
            {
                cid = page.getMetaData(CID_KEY);
            }
        }
        if (cid == null)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Beginning transient conversation...");
            }
            adapter.beginTransientConversation();
        }
        else
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Resuming non-transient conversation {}...", cid);
            }
            adapter.resumeConversation(cid);
        }
        cycle.setMetaData(CONVERSATION_STARTED_KEY, true);
    }

    @Override
    public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("Request handler {} scheduled.", handler.getClass().getSimpleName());
        }
        propagateToPageMetaData(handler);
        propagateToPageParameters(handler);
    }

    @Override
    public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url)
    {
        if (requiresPropagation())
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Rewriting url {} to append conversation id {}...", url.toString(), conversation.getId());
            }
            url.setQueryParameter(CID_PARAM, conversation.getId());
        }
        else if (url.getQueryParameter(CID_PARAM) != null)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Rewriting url {} to remove conversation id {}...", url.toString(), url.getQueryParameterValue(CID_PARAM));
            }
            url.removeQueryParameters(CID_PARAM);
        }
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private boolean conversationStarted(RequestCycle requestCycle)
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

    private boolean requiresPropagation()
    {
        return !conversation.isTransient();
    }

    private void propagateToPageMetaData(IRequestHandler handler)
    {
        Page page = getPage(handler);
        if (page != null)
        {
            if (requiresPropagation())
            {
                if (logger.isTraceEnabled())
                {
                    logger.trace("Saving non-transient conversation {} to current page's metadata...", conversation.getId());
                }
                page.setMetaData(CID_KEY, conversation.getId());
            }
            else
            {
                final String cid = page.getMetaData(CID_KEY);
                if (cid != null)
                {
                    if (logger.isTraceEnabled())
                    {
                        logger.trace("Removing ended conversation {} from current page's metadata...", cid);
                    }
                    page.setMetaData(CID_KEY, null);
                }
            }

        }
    }

    private void propagateToPageParameters(IRequestHandler handler)
    {
        PageParameters parameters = getPageParameters(handler);
        if (parameters != null)
        {
            if (requiresPropagation())
            {
                if (logger.isTraceEnabled())
                {
                    logger.trace("Saving non-transient conversation {} to page parameters...", conversation.getId());
                }
                parameters.set(CID_PARAM, conversation.getId());
            }
            else
            {
                final StringValue value = parameters.get(CID_PARAM);
                if (!value.isNull())
                {
                    if (logger.isTraceEnabled())
                    {
                        logger.trace("Removing ended conversation {} from page parameters...", value);
                    }
                    parameters.remove(CID_PARAM);
                }

            }
        }
    }
}
