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

package org.wicketopia.model.column;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.model.table.DefaultSortableCollectionDataProvider;
import org.wicketopia.testing.AbstractWicketopiaTestCase;
import org.wicketopia.util.EditableBean;

import java.util.ArrayList;
import java.util.List;

public class TestBeanPropertyColumn extends AbstractWicketopiaTestCase {
    @Test
    public void testRendering() {
        List<EditableBean> beans = new ArrayList<EditableBean>();
        final EditableBean bean1 = new EditableBean();
        beans.add(bean1);
        final SortableDataProvider<EditableBean, String> dataProvider = new DefaultSortableCollectionDataProvider<EditableBean>("gender", SortOrder.ASCENDING, beans);
        final List<IColumn<EditableBean, String>> columns = new ArrayList<IColumn<EditableBean, String>>();
        PropertyComponentFactory<EditableBean> factory = Wicketopia.get().createViewerFactory(EditableBean.class);
        final Context context = new Context(Context.LIST);
        columns.add(new BeanPropertyColumn<EditableBean>(factory, "gender", context));
        final DefaultDataTable<EditableBean, String> table = new DefaultDataTable<EditableBean, String>(DataTableTestPage.TABLE_ID, columns, dataProvider, Integer.MAX_VALUE);
        tester.startPage(new DataTableTestPage<EditableBean>(table));
        tester.assertNoErrorMessage();
    }

}
