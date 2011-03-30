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
