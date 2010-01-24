package org.wicketopia.editor;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class EditorContext implements Serializable
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private final Map<String,Object> attributes = new TreeMap<String,Object>();

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name)
    {
        return (T)attributes.get(name);
    }

    public <T> void setAttribute(String name, T value)
    {
        attributes.put(name, value);
    }
}
