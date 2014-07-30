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

package org.wicketopia.domdrides.component.link;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.domdrides.repository.Repository;
import org.testng.annotations.Test;
import org.wicketopia.domdrides.component.link.ajax.AjaxRemoveEntityLink;
import org.wicketopia.domdrides.util.Person;
import org.wicketopia.testing.AbstractWicketTestCase;

import static org.easymock.EasyMock.expectLastCall;
import static org.testng.Assert.assertTrue;

/**
 * @author James Carman
 */
public class TestAjaxRemoveEntityLink extends AbstractWicketTestCase {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private boolean afterRemoveCalled = false;

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    public void testEntityRemovedWhenClicked() {
        final Repository<Person, String> mockRepo = createMock(Repository.class);
        final Person person = new Person();
        mockRepo.remove(person);
        expectLastCall();
        replayAll();
        final AjaxRemoveEntityLink<Person, String> link = new AjaxRemoveEntityLink<Person, String>("link", mockRepo, new Model<Person>(person)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void afterRemove(Person entity, AjaxRequestTarget ajaxRequestTarget) {
                afterRemoveCalled = true;
            }
        };
        RemovePersonPage page = new RemovePersonPage(link);
        tester.startPage(page);
        tester.clickLink("link");
        assertTrue(afterRemoveCalled);
    }
}