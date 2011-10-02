/*
 * Copyright (c) 2011. Carman Consulting, Inc.
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

package org.wicketopia.layout.list;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;
import org.wicketopia.layout.view.CssBeanViewLayoutTestPage;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.EditableBean;
import org.wicketopia.util.Gender;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

public class TestBeanListLayoutPanel extends AbstractWicketTestCase
{
    @Test
    public void testWithViewer()
    {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        final Context context = new Context(Context.VIEW);
        tester.startPage(new BeanListLayoutTestPage(new BeanListLayoutPanel<EditableBean>(BeanListLayoutTestPage.PANEL_ID, EditableBean.class, Model.ofList(Collections.singletonList(bean)), context, plugin.createViewerFactory(EditableBean.class))));
        tester.assertRenderedPage(BeanListLayoutTestPage.class);
        /*tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Int Property");
        tester.assertModelValue("view:prop-div:2:prop-component", 123);
        tester.assertLabel("view:prop-div:3:prop-label", "Double Property");
        tester.assertModelValue("view:prop-div:3:prop-component", 456.0);
        tester.assertLabel("view:prop-div:4:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:4:prop-component", Gender.Male);*/

        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
