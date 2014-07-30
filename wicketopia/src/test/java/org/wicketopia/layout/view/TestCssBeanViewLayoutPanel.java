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

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.testing.AbstractWicketTestCase;
import org.wicketopia.util.EditableBean;
import org.wicketopia.util.Gender;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * @author James Carman
 */
public class TestCssBeanViewLayoutPanel extends AbstractWicketTestCase {
    @Test
    public void testWithViewer() {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        final IModel<EditableBean> model = new Model<EditableBean>(bean);
        final Context context = new Context(Context.VIEW);
        tester.startPage(new CssBeanViewLayoutTestPage(new CssBeanViewLayoutPanel<EditableBean>(CssBeanViewLayoutTestPage.PANEL_ID, EditableBean.class, model, context, plugin.createViewerFactory(EditableBean.class))));
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
    public void testCustomCssClass() {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        final IModel<EditableBean> model = new Model<EditableBean>(bean);
        final Context context = new Context(Context.CREATE);
        CssBeanViewLayoutPanel<EditableBean> panel = new CssBeanViewLayoutPanel<EditableBean>(CssBeanViewLayoutTestPage.PANEL_ID, EditableBean.class, model, context, plugin.createEditorFactory(EditableBean.class));
        panel.setCssClass("custom-css");
        tester.startPage(new CssBeanViewLayoutTestPage(panel));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), "custom-css");
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testWithEditor() {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        final IModel<EditableBean> model = new Model<EditableBean>(bean);
        final Context context = new Context(Context.CREATE);
        tester.startPage(new CssBeanViewLayoutTestPage(new CssBeanViewLayoutPanel<EditableBean>(CssBeanViewLayoutTestPage.PANEL_ID, EditableBean.class, model, context, plugin.createEditorFactory(EditableBean.class))));
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

    @Test
    public void testWithEnumeratedProperties() {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        final IModel<EditableBean> model = new Model<EditableBean>(bean);
        final Context context = new Context(Context.CREATE);
        tester.startPage(new CssBeanViewLayoutTestPage(new CssBeanViewLayoutPanel<EditableBean>(CssBeanViewLayoutTestPage.PANEL_ID, EditableBean.class, model, context, plugin.createEditorFactory(EditableBean.class), "stringProperty", "gender")));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component:editor", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:2:prop-component:ddc", Gender.Male);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), CssBeanViewLayoutPanel.DEFAULT_CSS_CLASS);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testWithEnumeratedPropertiesList() {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        final IModel<EditableBean> model = new Model<EditableBean>(bean);
        final Context context = new Context(Context.CREATE);
        tester.startPage(new CssBeanViewLayoutTestPage(new CssBeanViewLayoutPanel<EditableBean>(CssBeanViewLayoutTestPage.PANEL_ID, EditableBean.class, model, context, plugin.createEditorFactory(EditableBean.class), Arrays.asList("stringProperty", "gender"))));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component:editor", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:2:prop-component:ddc", Gender.Male);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), CssBeanViewLayoutPanel.DEFAULT_CSS_CLASS);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }
}
