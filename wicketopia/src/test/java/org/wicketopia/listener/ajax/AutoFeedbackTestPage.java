/*
 * Copyright (c) 2011. Carman Consulting, Inc.
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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * @since 1.0
 */
public class AutoFeedbackTestPage extends WebPage {
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public AutoFeedbackTestPage() {
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel.setOutputMarkupPlaceholderTag(true));
        add(new AjaxLink<Void>("messageLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                info("You clicked me!");
            }
        });
        add(new AjaxLink<Void>("noMessageLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Do nothing
            }
        });
    }
}
