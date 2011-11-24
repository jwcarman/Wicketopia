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
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.testing.AbstractWicketTestCase;
import org.wicketopia.util.EditableBean;
import org.wicketopia.util.Gender;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestBeanListLayoutPanel extends AbstractWicketTestCase
{
    @Test
    public void testWithViewer()
    {
        Wicketopia plugin = new Wicketopia();
        plugin.install(tester.getApplication());
        final EditableBean bean1 = new EditableBean();
        bean1.setStringProperty("Hello");
        bean1.setIntProperty(123);
        bean1.setGender(Gender.Male);
        final EditableBean bean2 = new EditableBean();
        bean2.setStringProperty("World");
        bean2.setIntProperty(987);
        bean2.setGender(Gender.Female);
        final Context context = new Context(Context.VIEW);
        IModel<List<? extends EditableBean>> model = Model.ofList(Arrays.asList(bean1, bean2));
        PropertyComponentFactory<EditableBean> factory = plugin.createViewerFactory(EditableBean.class);

        tester.startPage(new BeanListLayoutTestPage(new BeanListLayoutPanel<EditableBean>(BeanListLayoutTestPage.PANEL_ID, EditableBean.class, model, context, factory, "stringProperty", "intProperty", "gender")));

        tester.assertRenderedPage(BeanListLayoutTestPage.class);
        // Validate headers...
        tester.assertLabel("view:headers:1", "String Property");
        tester.assertLabel("view:headers:2", "Int Property");
        tester.assertLabel("view:headers:3", "Gender");
        // Validate rows...
        tester.assertLabel("view:rows:0:cells:0:component", "Hello");
        tester.assertLabel("view:rows:0:cells:1:component", "123");
        tester.assertLabel("view:rows:0:cells:2:component", "Male");
        tester.assertLabel("view:rows:1:cells:0:component", "World");
        tester.assertLabel("view:rows:1:cells:1:component", "987");
        tester.assertLabel("view:rows:1:cells:2:component", "Female");

        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
