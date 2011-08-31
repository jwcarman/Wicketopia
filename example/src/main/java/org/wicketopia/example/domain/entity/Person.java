/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.example.domain.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.wicketopia.builder.feature.annotation.metadata.DisplayName;
import org.wicketopia.builder.feature.annotation.metadata.Order;
import org.wicketopia.builder.feature.annotation.metadata.ViewerType;
import org.wicketopia.builder.feature.annotation.required.Required;
import org.wicketopia.builder.feature.annotation.validator.Email;
import org.wicketopia.builder.feature.annotation.validator.Pattern;
import org.wicketopia.example.domain.value.Gender;
import org.wicketopia.joda.annotation.DatePattern;
import org.wicketopia.security.annotation.VisibleForRole;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Person extends BaseEntity
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final int NINE_AM = 9;
    public static final int FIVE_PM = 17;

    private String firstName;
    private String lastName;
    private String ssn;
    private String email;
    private boolean smoker;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    private LocalDate dob;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsString")
    private LocalTime workDayBegin = new LocalTime(NINE_AM, 0);

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsString")
    private LocalTime workDayEnd = new LocalTime(FIVE_PM, 0);

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static Person createDummy()
    {
        Person dummy = new Person();
        dummy.setFirstName("Dummy");
        dummy.setLastName("Person");
        dummy.setEmail("dummy@person.com");
        dummy.setGender(Gender.Male);
        return dummy;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    @DatePattern("MM/dd/yyyy")
    public LocalDate getDob()
    {
        return dob;
    }

    public void setDob(LocalDate dob)
    {
        this.dob = dob;
    }

    @Email
    @Order(3)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Order(1)
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    @Required
    @Order(2)
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @DisplayName("SSN")
    @Order(4)
    @Pattern("^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$")
    @VisibleForRole("ROLE_ADMIN")
    public String getSsn()
    {
        return ssn;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    //@DatePattern("hh:mm a")
    public LocalTime getWorkDayBegin()
    {
        return workDayBegin;
    }

    public void setWorkDayBegin(LocalTime workDayBegin)
    {
        this.workDayBegin = workDayBegin;
    }

    public LocalTime getWorkDayEnd()
    {
        return workDayEnd;
    }

    public void setWorkDayEnd(LocalTime workDayEnd)
    {
        this.workDayEnd = workDayEnd;
    }

    @ViewerType("image-boolean")
    public boolean isSmoker()
    {
        return smoker;
    }

    public void setSmoker(boolean smoker)
    {
        this.smoker = smoker;
    }

//----------------------------------------------------------------------------------------------------------------------
// Canonical Methods
//----------------------------------------------------------------------------------------------------------------------

    public String toString()
    {
        return firstName + " " + lastName;
    }
}
