package org.wicketopia.util;

import org.domdrides.entity.UuidEntity;

/**
 * @author James Carman
 */
public class Person extends UuidEntity
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private String first;
    private String last;

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public String getFirst()
    {
        return first;
    }

    public void setFirst( String first )
    {
        this.first = first;
    }

    public String getLast()
    {
        return last;
    }

    public void setLast( String last )
    {
        this.last = last;
    }
}
