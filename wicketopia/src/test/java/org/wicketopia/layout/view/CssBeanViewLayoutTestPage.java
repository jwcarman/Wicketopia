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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.util.EditableBean;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class CssBeanViewLayoutTestPage extends WebPage
{
    public CssBeanViewLayoutTestPage(EditableBean bean, PropertyComponentFactory<EditableBean> factory)
    {
        final IModel<EditableBean> model = new Model<EditableBean>(bean);
        final Context context = new Context(Context.CREATE);
        add(new CssBeanViewLayoutPanel<EditableBean>("view", EditableBean.class, model, context, factory));
    }


}
