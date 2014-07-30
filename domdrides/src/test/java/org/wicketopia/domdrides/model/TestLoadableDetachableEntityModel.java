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

package org.wicketopia.domdrides.model;

import org.domdrides.repository.Repository;
import org.testng.annotations.Test;
import org.wicketopia.domdrides.util.Person;
import org.wicketopia.testing.AbstractTestCase;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertSame;

/**
 * @author James Carman
 */
public class TestLoadableDetachableEntityModel extends AbstractTestCase {
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    public void testGetObjectWithEntity() {
        final Repository<Person, String> repo = createMock(Repository.class);
        final Person expected = new Person();
        replayAll();
        LoadableDetachableEntityModel<Person, String> model = new LoadableDetachableEntityModel<Person, String>(repo, expected);
        final Person actual = model.getObject();
        assertSame(actual, expected);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetObjectWithId() {
        final Repository<Person, String> repo = createMock(Repository.class);
        final Person expected = new Person();
        expect(repo.getById(expected.getId())).andReturn(expected);
        replayAll();
        LoadableDetachableEntityModel<Person, String> model = new LoadableDetachableEntityModel<Person, String>(repo, expected.getId());
        Person actual = model.getObject();
        assertSame(actual, expected);
    }
}
