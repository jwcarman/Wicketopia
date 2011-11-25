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

package org.wicketopia.mapping.viewer;

import org.testng.annotations.Test;
import org.wicketopia.util.Gender;
import org.wicketopia.viewer.component.LabelPropertyViewer;

import static org.testng.Assert.assertEquals;

/**
 * @author James Carman
 */
public class TestDefaultViewerTypeMapping
{
    @Test
    public void testDefaultMappings()
    {
        final DefaultViewerTypeMapping mapping = new DefaultViewerTypeMapping();
        assertEquals(mapping.getTypeName(String.class), LabelPropertyViewer.TYPE_NAME);
        assertEquals(mapping.getTypeName(Gender.class), LabelPropertyViewer.TYPE_NAME);
    }
}
