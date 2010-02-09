package org.wicketopia.metadata.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;

public class PropertyMetaDataModel extends LoadableDetachableModel<PropertyMetaData>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final Class beanClass;
    private final String propertyName;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public PropertyMetaDataModel(PropertyMetaData object)
    {
        super(object);
        this.beanClass = object.getBeanMetaData().getBeanDescriptor().getBeanClass();
        this.propertyName = object.getPropertyDescriptor().getName();
    }

    public PropertyMetaDataModel(Class beanClass, String propertyName)
    {
        this.beanClass = beanClass;
        this.propertyName = propertyName;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    protected PropertyMetaData load()
    {
        return WicketopiaPlugin.get().getBeanMetadataFactory().getBeanMetaData(beanClass).getPropertyMetaData(propertyName);
    }
}
