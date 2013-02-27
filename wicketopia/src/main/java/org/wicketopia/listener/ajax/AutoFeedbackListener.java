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

package org.wicketopia.listener.ajax;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.List;
import java.util.Map;

/**
 * Add this to your AjaxRequestTarget and your feedback panels will automatically be included
 * in the AJAX response if they have messages.
 *
 * @since 1.0
 */
public class AutoFeedbackListener implements AjaxRequestTarget.IListener
{
//----------------------------------------------------------------------------------------------------------------------
// IListener Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public void onAfterRespond(Map<String, Component> map, AjaxRequestTarget.IJavaScriptResponse response)
    {
        // Do nothing!
    }

    @Override
    public void onBeforeRespond(Map<String, Component> map, final AjaxRequestTarget target)
    {
        target.getPage().visitChildren(IFeedback.class, new IVisitor<Component, Void>()
        {
            @Override
            public void component(Component component, IVisit<Void> visit)
            {
                if (component.getOutputMarkupId())
                {
                    target.add(component);
                }
                visit.dontGoDeeper();
            }
        });

    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private boolean hasMessages(FeedbackPanel feedbackPanel)
    {
        List<FeedbackMessage> feedbackMessages = feedbackPanel.getFeedbackMessagesModel().getObject();
        return feedbackMessages != null && !feedbackMessages.isEmpty();
    }
}
