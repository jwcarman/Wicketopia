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

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;

import java.util.List;

public class CssBeanViewLayoutPanel<T> extends BeanViewLayoutPanel<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static String DEFAULT_CSS_CLASS = "css-layout";
    private String cssClass = DEFAULT_CSS_CLASS;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CssBeanViewLayoutPanel(String id, Class<T> beanType, IModel<T> beanModel, Context context, PropertyComponentFactory<T> componentFactory)
    {
        super(id, beanType, beanModel, context, componentFactory);
        init();
    }

    public CssBeanViewLayoutPanel(String id, Class<T> beanType, IModel<T> beanModel, Context context, PropertyComponentFactory<T> componentFactory, List<String> propertyNames)
    {
        super(id, beanType, beanModel, context, componentFactory, propertyNames);
        init();
    }

    public CssBeanViewLayoutPanel(String id, Class<T> beanType, IModel<T> beanModel, Context context, PropertyComponentFactory<T> componentFactory, String... propertyNames)
    {
        super(id, beanType, beanModel, context, componentFactory, propertyNames);
        init();
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected void init()
    {
        final RepeatingView view = new RepeatingView("prop-div");
        for (String propertyName : getPropertyNames())
        {
            final Component editor = createPropertyComponent("prop-component", propertyName);
            final WebMarkupContainer div = new WebMarkupContainer(view.newChildId())
            {
                @Override
                public boolean isVisible()
                {
                    return editor.isVisible();
                }
            };
            div.add(editor);
            div.add(createPropertyLabel("prop-label", propertyName));
            view.add(div);
        }
        add(view);
    }

    @Override
    protected void onComponentTag(ComponentTag tag)
    {
        tag.getAttributes().put("class", cssClass);
    }

    public CssBeanViewLayoutPanel<T> setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
        return this;
    }
}
