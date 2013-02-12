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

package org.wicketopia.domdrides.model.repeater;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.domdrides.repository.PageableRepository;
import org.testng.annotations.Test;
import org.wicketopia.domdrides.model.LoadableDetachableEntityModel;
import org.wicketopia.domdrides.util.Person;
import org.wicketopia.testing.AbstractTestCase;

import java.util.Collections;

import static org.testng.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * @author James Carman
 */
public class TestPageableRepositoryDataProvider extends AbstractTestCase
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    public void testIterator()
    {
        final PageableRepository<Person, String> repo = createMock(PageableRepository.class);
        expect(repo.list(0, 10, "first", true)).andReturn(Collections.<Person>emptyList());
        expect(repo.list(0, 10, "last", false)).andReturn(Collections.<Person>emptyList());
        replayAll();
        PageableRepositoryDataProvider<Person, String> provider =
                new PageableRepositoryDataProvider<Person, String>(repo, "first", true);
        provider.iterator(0, 10);
        provider.setSort(new SortParam("last", false));
        provider.iterator(0, 10);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testModel()
    {
        final PageableRepository<Person, String> repo = createMock(PageableRepository.class);
        replayAll();
        PageableRepositoryDataProvider<Person, String> provider =
                new PageableRepositoryDataProvider<Person, String>(repo, "first", true);
        final Person p = new Person();
        IModel<Person> model = provider.model(p);
        assertSame(model.getObject(), p);
        assertTrue(model instanceof LoadableDetachableEntityModel);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSize()
    {
        final PageableRepository<Person, String> repo = createMock(PageableRepository.class);
        final int expected = 100;
        expect(repo.size()).andReturn(expected);
        replayAll();
        PageableRepositoryDataProvider<Person, String> provider =
                new PageableRepositoryDataProvider<Person, String>(repo, "first");
        long actual = provider.size();
        assertEquals(actual, expected);
    }
}
