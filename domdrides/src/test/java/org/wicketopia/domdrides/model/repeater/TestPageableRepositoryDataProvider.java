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

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.domdrides.repository.PageableRepository;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.wicketopia.domdrides.model.LoadableDetachableEntityModel;
import org.wicketopia.domdrides.util.Person;

import java.util.Collections;

/**
 * @author James Carman
 */
public class TestPageableRepositoryDataProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    @SuppressWarnings( "unchecked" )
    public void testIterator()
    {
        final Mockery context = new Mockery();
        final PageableRepository<Person, String> repo = context.mock(PageableRepository.class);
        context.checking(new Expectations()
        {
            {
                one(repo).list(0, 10, "first", true);
                will(returnValue(Collections.<Person>emptyList()));
                one(repo).list(0, 10, "last", false);
                will(returnValue(Collections.<Person>emptyList()));
            }
        });
        PageableRepositoryDataProvider<Person, String> provider =
                new PageableRepositoryDataProvider<Person, String>(repo, "first", true);
        provider.iterator(0, 10);
        provider.setSort(new SortParam("last", false));
        provider.iterator(0, 10);
        context.assertIsSatisfied();
    }

    @Test
    @SuppressWarnings( "unchecked" )
    public void testModel()
    {
        final Mockery context = new Mockery();
        final PageableRepository<Person, String> repo = context.mock(PageableRepository.class);
        PageableRepositoryDataProvider<Person, String> provider =
                new PageableRepositoryDataProvider<Person, String>(repo, "first", true);
        final Person p = new Person();
        IModel<Person> model = provider.model(p);
        assertSame(model.getObject(), p);
        assertTrue(model instanceof LoadableDetachableEntityModel);
        context.assertIsSatisfied();
    }

    @Test
    @SuppressWarnings( "unchecked" )
    public void testSize()
    {
        final Mockery context = new Mockery();
        final PageableRepository<Person, String> repo = context.mock(PageableRepository.class);
        final int expected = 100;
        context.checking(new Expectations()
        {
            {
                one(repo).size();
                will(returnValue(expected));
            }
        });
        PageableRepositoryDataProvider<Person, String> provider =
                new PageableRepositoryDataProvider<Person, String>(repo, "first");
        int actual = provider.size();
        assertEquals(actual, expected);
        context.assertIsSatisfied();
    }
}
