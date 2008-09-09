package org.wicketopia.model.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @auothor James Carman
 */
public abstract class LabelColumn<T> extends AbstractColumn<T>
{
    private static final long serialVersionUID = 1L;

    protected LabelColumn( IModel<String> displayModel )
    {
        super(displayModel);
    }

    protected LabelColumn( IModel<String> displayModel, String sortProperty )
    {
        super(displayModel, sortProperty);
    }

    public void populateItem( Item<ICellPopulator<T>> iCellPopulatorItem, String componentId, IModel<T> rowModel )
    {
        new Label(componentId, createLabelModel(rowModel));
    }

    protected abstract IModel<?> createLabelModel( IModel<T> rowModel );
}
