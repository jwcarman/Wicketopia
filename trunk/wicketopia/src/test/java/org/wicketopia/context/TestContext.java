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

package org.wicketopia.context;

import org.apache.wicket.MetaDataKey;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
/**
 * @author James Carman
 */
public class TestContext
{
    @Test
    public void testName()
    {
        final Context context = new Context("blah");
        assertEquals(context.getName(), "blah");
    }

    @Test
    public void testAttributes()
    {
        final MetaDataKey<String> key1 = new MetaDataKey<String>() {};

        final MetaDataKey<String> key2 = new MetaDataKey<String>(){};

        final Context context = new Context("blah");
        context.setAttribute(key1, "value1");
        assertEquals(context.getAttribute(key1), "value1");
        assertNull(context.getAttribute(key2));

    }

}
