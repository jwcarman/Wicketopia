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

package org.wicketopia.domdrides.model;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.testng.annotations.Test;
import org.domdrides.repository.Repository;
import org.wicketopia.domdrides.util.Person;

import static org.testng.Assert.*;

/**
 * @author James Carman
 */
public class TestLoadableDetachableEntityModel
{
    @Test
    @SuppressWarnings("unchecked")
    public void testGetObjectWithId()
    {
        final Mockery context = new Mockery();
        final Repository<Person,String> repo = context.mock(Repository.class);
        final Person expected = new Person();
        context.checking(new Expectations(){{
            one(repo).getById("1"); will(returnValue(expected) );
        }});
        LoadableDetachableEntityModel<Person,String> model = new LoadableDetachableEntityModel<Person,String>(repo, "1");
        Person actual = model.getObject();
        assertSame(actual, expected);
        context.assertIsSatisfied();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetObjectWithEntity()
    {
        final Mockery context = new Mockery();
        final Repository<Person,String> repo = context.mock(Repository.class);
        final Person expected = new Person();
        LoadableDetachableEntityModel<Person,String> model = new LoadableDetachableEntityModel<Person,String>(repo, expected);
        Person actual = model.getObject();
        assertSame(actual, expected);
        context.assertIsSatisfied();
    }
}
