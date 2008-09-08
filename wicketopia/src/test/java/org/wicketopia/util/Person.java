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
    private Gender gender;

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
