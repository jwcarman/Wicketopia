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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockWebRequest;
import org.apache.wicket.mock.MockWebResponse;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.tester.WicketTester;
import org.easymock.EasyMockSupport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

public class TestCdiInjector extends EasyMockSupport
{
    private BeanManager beanManager;

    @BeforeClass
    public void createMocks()
    {
        beanManager = createMock(BeanManager.class);
    }

    @BeforeMethod
    public void setUp()
    {
        CdiUtils.clearCache();
        resetAll();
    }

    @Test
    public void testInjectingSession() throws Exception
    {
        WicketTester tester = new WicketTester();
        tester.getApplication().getSessionListeners().add(new CdiInjector(beanManager));
        expectInjection(WebSession.class);
        replayAll();
        ThreadContext.setSession(null);
        Session.get();
        verifyAll();
    }

    @Test
    public void testInjectingBehavior() throws Exception
    {
        WicketTester tester = new WicketTester();
        tester.getApplication().getBehaviorInstantiationListeners().add(new CdiInjector(beanManager));
        expectInjection(AttributeModifier.class);
        replayAll();
        new AttributeModifier("test", "test");
        verifyAll();
    }

    private <T> void expectInjection(Class<T> type)
    {
        InjectionTarget<T> injectionTarget = createMock(InjectionTarget.class);
        CreationalContext<T> creationalContext = createMock(CreationalContext.class);
        AnnotatedType<T> annotatedType = createMock(AnnotatedType.class);
        expect(beanManager.createAnnotatedType(type)).andReturn(annotatedType);
        expect(beanManager.createInjectionTarget(annotatedType)).andReturn(injectionTarget);
        expect(beanManager.createCreationalContext((Contextual<T>) null)).andReturn(creationalContext);
        injectionTarget.inject(anyObject(type), eq(creationalContext));
        expectLastCall();
        injectionTarget.postConstruct(anyObject(type));
        expectLastCall();
    }

    @Test
    public void testInjectingComponent() throws Exception
    {
        WicketTester tester = new WicketTester();
        tester.getApplication().getComponentInstantiationListeners().add(new CdiInjector(beanManager));
        expectInjection(Label.class);
        replayAll();
        new Label("test", "test");
        verifyAll();
    }
}

