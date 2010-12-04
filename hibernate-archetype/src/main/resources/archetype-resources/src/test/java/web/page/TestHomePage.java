/*
 * Copyright (c) 2010 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ${package}.web.page;

import ${package}.domain.entity.Widget;
import ${package}.domain.repository.WidgetRepository;
import ${package}.web.util.AbstractWicketTestCase;
import org.jmock.Expectations;
import org.testng.annotations.Test;

import java.util.Collections;

public class TestHomePage extends AbstractWicketTestCase
{
    @Test
    public void testRenderMyPage()
	{
        final WidgetRepository widgetRepository = mockery.mock(WidgetRepository.class);
        mockery.checking(new Expectations()
        {
            {
                one(widgetRepository).size(); will(returnValue(0));
                one(widgetRepository).list(0,0,"name",true); will(returnValue(Collections.<Widget>emptyList()));
            }
        });
        springContext.putBean("widgetRepository", widgetRepository);

        tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
	}
}
