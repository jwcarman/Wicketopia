package org.wicketopia.editor;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

import java.io.Serializable;
import java.util.*;

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

    public List<PropertyMetaData> getPropertyMetaData(String... skippedProperties)
    {
        final BeanMetaData beanMetaData = getBeanMetaData();
        Set<String> propertyNames = beanMetaData.getPropertyNames();
        propertyNames.removeAll(Arrays.asList(skippedProperties));
        final List<PropertyMetaData> propertyMetaDatas = new ArrayList<PropertyMetaData>(propertyNames.size());
        for (String propertyName : propertyNames)
        {
            propertyMetaDatas.add(beanMetaData.getPropertyMetaData(propertyName));
        }
        Collections.sort(propertyMetaDatas, new Comparator<PropertyMetaData>()
        {
            public int compare(PropertyMetaData o1, PropertyMetaData o2)
            {
                return WicketopiaPropertyMetaData.get(o1).compareTo(WicketopiaPropertyMetaData.get(o2));
            }
        });
        return propertyMetaDatas;
    }

    public List<String> getPropertyNames(String... skippedProperties)
    {
        final List<PropertyMetaData> propertyMetaDatas = getPropertyMetaData(skippedProperties);
        List<String> propertyNames = new ArrayList<String>(propertyMetaDatas.size());
        for (PropertyMetaData propertyMetaData : propertyMetaDatas)
        {
            propertyNames.add(propertyMetaData.getPropertyDescriptor().getName());
        }
        return propertyNames;
    }

    private PropertyMetaData getPropertyMetaData(String propertyName)
    {
        BeanMetaData beanMetaData = getBeanMetaData();
        return beanMetaData.getPropertyMetaData(propertyName);
    }

    private BeanMetaData getBeanMetaData()
    {
        return WicketopiaPlugin.get().getBeanMetadataFactory().getBeanMetaData(beanClass);
    }

    public Component createPropertyEditor(String componentId, String propertyName)
    {
        PropertyMetaData propertyMetaData = getPropertyMetaData(propertyName);
        return WicketopiaPlugin.get().getPropertyEditorFactory().createPropertyEditor(componentId, propertyMetaData, new PropertyModel(beanModel, propertyName), editorContext);
    }

    public Label createPropertyLabel(String componentId, String propertyName)
    {
        return new PropertyLabel(componentId, getPropertyMetaData(propertyName));
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
