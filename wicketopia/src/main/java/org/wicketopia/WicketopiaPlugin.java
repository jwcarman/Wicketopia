package org.wicketopia;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.editor.def.DefaultPropertyEditorFactory;
import org.wicketopia.metadata.BeanMetadataFactory;
import org.wicketopia.metadata.def.DefaultBeanMetadataFactory;

public class WicketopiaPlugin
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static MetaDataKey<WicketopiaPlugin> META_KEY = new WicketopiaPluginKey();

    private BeanMetadataFactory beanMetadataFactory = new DefaultBeanMetadataFactory();
    private PropertyEditorFactory propertyEditorFactory = new DefaultPropertyEditorFactory();

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static WicketopiaPlugin get()
    {
        return WebApplication.get().getMetaData(META_KEY);
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public BeanMetadataFactory getBeanMetadataFactory()
    {
        return beanMetadataFactory;
    }

    public void setBeanMetadataFactory(BeanMetadataFactory beanMetadataFactory)
    {
        this.beanMetadataFactory = beanMetadataFactory;
    }

    public PropertyEditorFactory getPropertyEditorFactory()
    {
        return propertyEditorFactory;
    }

    public void setPropertyEditorFactory(PropertyEditorFactory propertyEditorFactory)
    {
        this.propertyEditorFactory = propertyEditorFactory;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public void install(WebApplication webApplication)
    {
        webApplication.setMetaData(META_KEY, this);
    }

//**********************************************************************************************************************
// Inner Classes
//**********************************************************************************************************************

    private static class WicketopiaPluginKey extends MetaDataKey<WicketopiaPlugin>
    {
    }
}
