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

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;

/**
 * @author James Carman
 */
public class BeanPropertyColumn<T> implements IColumn<T>
{
    private final PropertyComponentFactory<T> factory;
    private final String propertyName;
    private final Context context;

    public BeanPropertyColumn(PropertyComponentFactory<T> factory, String propertyName, Context context)
    {
        this.factory = factory;
        this.propertyName = propertyName;
        this.context = context;
    }

    @Override
    public String getSortProperty()
    {
        return propertyName;
    }

    @Override
    public boolean isSortable()
    {
        return true;
    }

    @Override
    public void detach()
    {

    }

    @Override
    public Component getHeader(String componentId)
    {
        return factory.createPropertyLabel(componentId, propertyName);
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel)
    {
        cellItem.add(factory.createPropertyComponent(componentId, rowModel, propertyName, context));
    }
}
