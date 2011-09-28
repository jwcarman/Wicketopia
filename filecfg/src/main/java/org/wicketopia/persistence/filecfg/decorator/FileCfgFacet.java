package org.wicketopia.persistence.filecfg.decorator;

import java.io.Serializable;
import java.util.Properties;

import org.metastopheles.FacetKey;

public class FileCfgFacet implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static final FacetKey<FileCfgFacet> FACET_KEY = new FacetKey<FileCfgFacet>()
    {
    };

    private Properties properties;

    public FileCfgFacet(Properties properties)
    {
        this.properties = properties;
    }

    public Properties getProperties()
    {
        return properties;
    }
}
