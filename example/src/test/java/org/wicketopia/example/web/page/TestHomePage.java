/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.example.web.page;

import org.jmock.Expectations;
import org.testng.annotations.Test;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.example.domain.repository.WidgetRepository;
import org.wicketopia.example.web.util.AbstractWicketTestCase;
import org.wicketopia.persistence.PersistenceProvider;

public class TestHomePage extends AbstractWicketTestCase
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void testRenderMyPage()
    {
        final WidgetRepository widgetRepository = mockery.mock(WidgetRepository.class);
        mockery.checking(new Expectations()
        {
            {
                atLeast(1).of(widgetRepository).size();
                will(returnValue(0));
            }
        });
        final PersistenceProvider persistenceProvider = mockery.mock(PersistenceProvider.class);
        springContext.putBean("persistenceProvider", persistenceProvider);
        springContext.putBean("widgetRepository", widgetRepository);
        new WicketopiaPlugin().install(tester.getApplication());
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }
}
