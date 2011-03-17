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

package ${package}.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.model.Model;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wicketopia.model.LoadableDetachableEntityModel;
import org.wicketopia.model.repeater.PageableRepositoryDataProvider;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import ${package}.domain.repository.WidgetRepository;
import ${package}.domain.entity.Widget;

/**
 * Homepage
 */
public class HomePage extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WidgetRepository widgetRepository;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public HomePage()
    {
        List<IColumn<?>> columns = new ArrayList<IColumn<?>>();
        columns.add(new PropertyColumn<String>(new Model<String>("Name"), "name", "name"));
        add(new AjaxFallbackDefaultDataTable<Widget>("table", columns, new PageableRepositoryDataProvider<Widget,String>(widgetRepository, "name"), 20));
    }
}
