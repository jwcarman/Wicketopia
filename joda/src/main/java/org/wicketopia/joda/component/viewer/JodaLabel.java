package org.wicketopia.joda.component.viewer;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketopia.joda.util.format.JodaFormatSupport;
import org.wicketopia.viewer.component.LabelPropertyViewer;

/**
 * @author James Carman
 */
public class JodaLabel<T> extends LabelPropertyViewer
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaLabel(String id, IModel<T> model, JodaFormatSupport<T> formatSupport)
    {
        super(id, new Model<String>(""));
        setDefaultModel(new JodaLabelModel<T>(formatSupport, model));
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class JodaLabelModel<T> extends AbstractReadOnlyModel<String>
    {
        private final IModel<T> inner;
        private final JodaFormatSupport<T> formatSupport;

        private JodaLabelModel(JodaFormatSupport<T> formatSupport, IModel<T> inner)
        {
            this.formatSupport = formatSupport;
            this.inner = inner;
        }

        @Override
        public String getObject()
        {
            return formatSupport.convertToString(inner.getObject(), getLocale());
        }

        @Override
        public void detach()
        {
            super.detach();
            inner.detach();
        }
    }
}
