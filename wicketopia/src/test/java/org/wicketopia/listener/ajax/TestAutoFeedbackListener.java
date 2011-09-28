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

import org.apache.wicket.util.tester.Result;
import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.Test;
import org.wicketopia.util.AbstractWicketTestCase;

import static org.testng.Assert.assertTrue;


/**
 * @since 1.0
 */
public class TestAutoFeedbackListener extends AbstractWicketTestCase
{
    @Test
    public void testWithMessages()
    {
        WicketTester tester = new WicketTester();
        tester.getApplication().getAjaxRequestTargetListeners().add(new AutoFeedbackListener());
        tester.startPage(new AutoFeedbackTestPage());
        tester.clickLink("messageLink");
        tester.assertComponentOnAjaxResponse("feedback");
        tester.clickLink("noMessageLink");
        Result result = tester.isComponentOnAjaxResponse(tester.getComponentFromLastRenderedPage("feedback"));
        assertTrue(result.wasFailed());

    }


}
