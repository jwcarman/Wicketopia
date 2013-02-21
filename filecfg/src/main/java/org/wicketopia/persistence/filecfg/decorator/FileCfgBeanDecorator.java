package org.wicketopia.persistence.filecfg.decorator;

import org.metastopheles.BeanMetaData;
import org.metastopheles.MetaDataDecorator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileCfgBeanDecorator implements MetaDataDecorator<BeanMetaData>
{

    // ----------------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------------

    public static final String WICKETOPIA_CFG_EXTENSION = ".wicketopia.properties";

    public static final String IGNORED = "ignored";
    public static final String ENABLED = "enabled";
    public static final String DISABLED = "disabled";
    public static final String HIDDEN = "hidden";
    public static final String VISIBLE = "visible";
    public static final String ORDER_QUAL = ":";
    public static final String ORDER = "order" + ORDER_QUAL;

    // ----------------------------------------------------------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------------------------------------------------------

    public FileCfgBeanDecorator()
    {
    }

    // ----------------------------------------------------------------------------------------------------------------------
    // MetaDataDecorator Implementation
    // ----------------------------------------------------------------------------------------------------------------------

    @Override
    public void decorate(BeanMetaData metaData)
    {
        InputStream resourceStream = getConfigurationStream(metaData);
        if (resourceStream != null)
        {
            Properties properties = new Properties();
            try
            {
                properties.load(resourceStream);
                metaData.setFacet(FileCfgFacet.FACET_KEY, new FileCfgFacet(properties));
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------------------------
    // Other Methods
    // ----------------------------------------------------------------------------------------------------------------------

    private InputStream getConfigurationStream(BeanMetaData metaData)
    {
        Class<?> beanClass = metaData.getBeanDescriptor().getBeanClass();
        String resourceName = metaData.getBeanDescriptor().getName();
        return beanClass.getResourceAsStream(resourceName + WICKETOPIA_CFG_EXTENSION);
    }
}
