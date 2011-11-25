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

import org.easymock.EasyMockSupport;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.*;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

public class TestCdiUtils extends EasyMockSupport
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private BeanManager beanManager;
    private InjectionTarget<SampleBean> injectionTarget;
    private CreationalContext<SampleBean> creationalContext;
    private final SampleBean bean = new SampleBean();

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @BeforeClass
    public void createMocks()
    {
        beanManager = createMock(BeanManager.class);
        injectionTarget = createMock(InjectionTarget.class);
        creationalContext = createMock(CreationalContext.class);
    }

    @BeforeMethod
    public void setUp()
    {
        CdiUtils.clearCache();
        resetAll();
        expect(beanManager.createAnnotatedType(SampleBean.class)).andReturn(null);
        expect(beanManager.createInjectionTarget((AnnotatedType<SampleBean>) null)).andReturn(injectionTarget);
    }

    @Test
    public void testInject()
    {
        expect(beanManager.createCreationalContext((Contextual<SampleBean>) null)).andReturn(creationalContext);
        injectionTarget.inject(bean, creationalContext);
        expectLastCall();

        replayAll();
        CdiUtils.inject(bean, beanManager);
        verifyAll();
    }

    @Test
    public void testPostConstruct()
    {
        injectionTarget.postConstruct(bean);
        expectLastCall();
        replayAll();
        CdiUtils.postConstruct(bean, beanManager);
        verifyAll();
    }

    @Test
    public void testPreDestroy()
    {
        injectionTarget.preDestroy(bean);
        expectLastCall();
        replayAll();
        CdiUtils.preDestroy(bean, beanManager);
        verifyAll();
    }

    @Test
    public void testInjectionTargetCaching()
    {
        expect(beanManager.createCreationalContext((Contextual<SampleBean>) null)).andReturn(creationalContext);
        expect(beanManager.createCreationalContext((Contextual<SampleBean>) null)).andReturn(creationalContext);
        injectionTarget.inject(bean, creationalContext);
        expectLastCall().times(2);

        replayAll();
        CdiUtils.inject(bean, beanManager);
        CdiUtils.inject(bean, beanManager);
        verifyAll();
    }

}
