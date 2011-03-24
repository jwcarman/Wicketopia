/*
 * Copyright (c) 2011 Carman Consulting, Inc.
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

package org.wicketopia.domdrides.component.link;

import org.wicketopia.domdrides.util.AbstractWicketTestCase;
import org.wicketopia.domdrides.util.Person;

import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import org.jmock.Expectations;
import static org.testng.Assert.*;
import org.apache.wicket.model.Model;

/**
 * @author James Carman
 */
public class TestRemoveEntityLink extends AbstractWicketTestCase
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private boolean afterRemoveCalled = false;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    public void testEntityRemovedWhenClicked()
    {
        final Repository<Person,String> mockRepo = mockery.mock(Repository.class);
        final Person person = new Person();
        mockery.checking(new Expectations() {{
           one(mockRepo).remove(person);
        }});
        final RemoveEntityLink<Person,String> link = new RemoveEntityLink<Person,String>("link", mockRepo, new Model<Person>(person))
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void afterRemove( Person entity )
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
