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

package ${package}.web.util;

import ${package}.web.application.WicketApplication;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractWicketTestCase
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected AnnotApplicationContextMock springContext;
    protected WicketTester tester;
    protected Mockery mockery;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @AfterMethod
    public void assertMockeryIsSatisfied()
    {
        mockery.assertIsSatisfied();
    }

    @BeforeMethod
    public void constructWicketTester()
    {
        mockery = new Mockery();
        springContext = new AnnotApplicationContextMock();
        final WicketApplication application = new WicketApplication();
        application.setApplicationContext(springContext);
        tester = new WicketTester(application);
    }
}
