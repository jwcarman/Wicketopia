package org.wicketopia;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.protocol.http.WebApplication;
import org.metastopheles.BeanMetaDataFactory;
import org.metastopheles.annotation.ClasspathScanner;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.editor.def.DefaultPropertyEditorFactory;

public class WicketopiaPlugin
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static MetaDataKey<WicketopiaPlugin> META_KEY = new WicketopiaPluginKey();

    private BeanMetaDataFactory beanMetadataFactory = new BeanMetaDataFactory();
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

    public BeanMetaDataFactory getBeanMetadataFactory()
    {
        return beanMetadataFactory;
    }

    public void setBeanMetadataFactory(BeanMetaDataFactory beanMetadataFactory)
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
        new ClasspathScanner().appendTo(beanMetadataFactory);
        webApplication.setMetaData(META_KEY, this);
    }

//**********************************************************************************************************************
// Inner Classes
//**********************************************************************************************************************

    private static class WicketopiaPluginKey extends MetaDataKey<WicketopiaPlugin>
    {
    }
}
