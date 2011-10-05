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

package org.wicketopia.editor.component.property;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.tester.FormTester;
import org.metastopheles.BeanMetaData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.EditableBean;

public class TestCheckBoxPropertyEditor extends AbstractWicketTestCase
{
    @BeforeClass
    public void installWicketopia()
    {
        Wicketopia.install();
    }

    @Test
    public void testBinding()
    {
        BeanMetaData beanMetaData = Wicketopia.get().getBeanMetaData(EditableBean.class);
        EditableBean bean = new EditableBean();
        final IModel<Boolean> model = new PropertyModel<Boolean>(bean,"bool");
        bean.setBool(true);
        EditorBuilder propertyEditor = CheckBoxPropertyEditor.getProvider().createPropertyEditor("editor", beanMetaData.getPropertyMetaData("bool"), model);
        final EditorTestPage page = new EditorTestPage(propertyEditor.build());
        tester.startPage(page);
        tester.assertModelValue("form:editor:editor", Boolean.TRUE);
        FormTester formTester = tester.newFormTester("form");
        formTester.setValue("editor:editor", false);
        formTester.submit();
        tester.assertModelValue("form:editor:editor", Boolean.FALSE);

    }
}
