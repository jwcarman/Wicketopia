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

package org.wicketopia.layout.view;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import org.wicketopia.Wicketopia;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.EditableBean;
import org.wicketopia.util.Gender;

/**
 * @author James Carman
 */
public class TestCssBeanViewLayoutPanel extends AbstractWicketTestCase
{
    @Test
    public void testWithViewer()
    {
        Wicketopia plugin = new Wicketopia();
        plugin.initialize();
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        tester.startPage(new CssBeanViewLayoutTestPage(bean, plugin.createViewerFactory(EditableBean.class)));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Int Property");
        tester.assertModelValue("view:prop-div:2:prop-component", 123);
        tester.assertLabel("view:prop-div:3:prop-label", "Double Property");
        tester.assertModelValue("view:prop-div:3:prop-component", 456.0);
        tester.assertLabel("view:prop-div:4:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:4:prop-component", Gender.Male);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), CssBeanViewLayoutPanel.DEFAULT_CSS_CLASS);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testWithEditor()
    {
        Wicketopia plugin = new Wicketopia();
        plugin.initialize();
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        tester.startPage(new CssBeanViewLayoutTestPage(bean, plugin.createEditorFactory(EditableBean.class)));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component:editor", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Int Property");
        tester.assertModelValue("view:prop-div:2:prop-component:editor", 123);
        tester.assertLabel("view:prop-div:3:prop-label", "Double Property");
        tester.assertModelValue("view:prop-div:3:prop-component:editor", 456.0);
        tester.assertLabel("view:prop-div:4:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:4:prop-component:ddc", Gender.Male);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), CssBeanViewLayoutPanel.DEFAULT_CSS_CLASS);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }
}
