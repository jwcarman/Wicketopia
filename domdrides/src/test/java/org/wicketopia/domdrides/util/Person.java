package org.wicketopia.domdrides.util;

import org.domdrides.entity.UuidEntity;

/**
 * @version 1.0
 */
public class Person extends UuidEntity
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private String first;
    private String last;
    private Gender gender;
    private String multiWordProperty;

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public String getMultiWordProperty()
    {
        return multiWordProperty;
    }

    public void setMultiWordProperty( String multiWordProperty )
    {
        this.multiWordProperty = multiWordProperty;
    }

    public String getFirst()
    {
        return first;
    }

    public void setFirst( String first )
    {
        this.first = first;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender( Gender gender )
    {
        this.gender = gender;
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