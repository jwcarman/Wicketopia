package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanEditorHelper<T> implements Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Class<T> beanClass;
    private final IModel<T> beanModel;
    private final EditorContext editorContext = new EditorContext();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public BeanEditorHelper(Class<T> beanClass, IModel<T> beanModel)
    {
        this.beanClass = beanClass;
        this.beanModel = beanModel;
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public EditorContext getEditorContext()
    {
        return editorContext;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public ListView<String> createEditorsView(String componentId, String... skippedProperties)
    {
        final ListView<String> listView = new EditorListView(componentId, skippedProperties);
        listView.setReuseItems(true);
        return listView;

    }

    public List<String> getPropertyNames(String... skippedProperties)
    {
        List<WicketopiaPropertyMetaData> metas = getBeanMetadata().getAllPropertyMetadata(skippedProperties);
        List<String> names = new ArrayList<String>(metas.size());
        for (WicketopiaPropertyMetaData meta : metas)
        {
            names.add(meta.getPropertyName());
        }
        return names;
    }

    private WicketopiaPropertyMetaData getPropertyMetadata(String propertyName)
    {
        BeanMetadata<T> beanMetadata = getBeanMetadata();
        return beanMetadata.getPropertyMetadata(propertyName);
    }

    private BeanMetaData<T> getBeanMetadata()
    {
        return WicketopiaPlugin.get().getBeanMetadataFactory().getBeanMetadata(beanClass);
    }

    public Component createPropertyEditor(String componentId, String propertyName)
    {
        WicketopiaPropertyMetaData propertyMetadata = getPropertyMetadata(propertyName);
        return WicketopiaPlugin.get().getPropertyEditorFactory().createPropertyEditor(componentId, propertyMetadata, new PropertyModel(beanModel, propertyName), editorContext);
    }

    public Label createPropertyLabel(String componentId, String propertyName)
    {
        return new PropertyLabel(componentId, getPropertyMetadata(propertyName));
    }

    private class EditorListView extends ListView<String>
    {
        public EditorListView(String componentId, String... skippedProperties)
        {
            super(componentId, BeanEditorHelper.this.getPropertyNames(skippedProperties));
        }

        @Override
        protected void populateItem(ListItem<String> item)
        {
            final String propertyName = item.getModelObject();
            item.add(createPropertyLabel("label", propertyName));
            item.add(createPropertyEditor("editor", propertyName));
        }
    }
}
