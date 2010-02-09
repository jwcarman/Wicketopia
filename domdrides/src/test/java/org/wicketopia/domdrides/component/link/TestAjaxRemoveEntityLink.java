/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.domdrides.component.link;

import org.wicketopia.domdrides.util.AbstractWicketTestCase;
import org.wicketopia.domdrides.util.Person;
import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import org.jmock.Expectations;
import static org.testng.Assert.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * @author James Carman
 */
public class TestAjaxRemoveEntityLink extends AbstractWicketTestCase
{
    private boolean afterRemoveCalled = false;

    @Test
    @SuppressWarnings("unchecked")
    public void testEntityRemovedWhenClicked()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).remove(person);
        }});
        final AjaxRemoveEntityLink<Person,String> link = new AjaxRemoveEntityLink<Person,String>("link", mockRepo, new Model<Person>(person))
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void afterRemove( Person entity, AjaxRequestTarget ajaxRequestTarget )
            {
                afterRemoveCalled = true;
            }
        };
        RemovePersonPage page = new RemovePersonPage(link);
        tester.startPage(page);
        tester.clickLink("link");
        assertTrue(afterRemoveCalled);
    }
}