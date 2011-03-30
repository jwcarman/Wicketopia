/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.util;

import java.io.Serializable;

/**
 * @author James Carman
 */
public class Person implements Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;
    private String first;
    private String last;
    private Gender gender;
    private String multiWordProperty;
    private Integer ssn;

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

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

    public String getMultiWordProperty()
    {
        return multiWordProperty;
    }

    public void setMultiWordProperty( String multiWordProperty )
    {
        this.multiWordProperty = multiWordProperty;
    }

    public Integer getSsn()
    {
        return ssn;
    }

    public void setSsn(Integer ssn)
    {
        this.ssn = ssn;
    }
}
