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

package org.wicketopia.model.label;

import org.apache.wicket.model.Model;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPluralizedModel
{
    boolean detached = false;

    @Test
    public void testWithSingular()
    {
        Model<String> singularModel = new Model<String>("Person");
        final PluralizedModel model = new PluralizedModel(singularModel);
        assertEquals(model.getObject(), "Persons");
    }

    @Test
    public void testDetach()
    {
        Model<String> singularModel = new Model<String>("Person")
        {
            @Override
            public void detach()
            {
                detached = true;
            }
        };
        final PluralizedModel model = new PluralizedModel(singularModel);
        model.getObject();
        model.detach();
        assertTrue(detached );
    }

}
