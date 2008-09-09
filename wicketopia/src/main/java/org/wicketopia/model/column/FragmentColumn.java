package org.wicketopia.model.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @sine 1.0
 */
public abstract class FragmentColumn<T> extends AbstractColumn<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    protected FragmentColumn( IModel<String> displayModel )
    {
        super(displayModel);
    }

    protected FragmentColumn( IModel<String> displayModel, String sortProperty )
    {
        super(displayModel, sortProperty);
    }

//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract Fragment createFragment( String componentId, IModel<T> model );

//**********************************************************************************************************************
// ICellPopulator Implementation
//**********************************************************************************************************************

    public void populateItem( Item<ICellPopulator<T>> item, String componentId, IModel<T> itemModel )
    {
        item.add(createFragment(componentId, itemModel));
    }
}
