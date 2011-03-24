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

package org.wicketopia.example.domain.entity;

import org.domdrides.entity.UuidEntity;
import org.wicketopia.editor.annotation.metadata.DisplayName;
import org.wicketopia.editor.annotation.metadata.EditorType;
import org.wicketopia.editor.annotation.metadata.Ignored;
import org.wicketopia.editor.annotation.metadata.Order;
import org.wicketopia.editor.annotation.required.Required;
import org.wicketopia.editor.annotation.validator.*;
import org.wicketopia.editor.annotation.visible.Hidden;
import org.wicketopia.example.domain.value.WidgetType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Widget extends UuidEntity
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String anotherProperty;
    private WidgetType widgetType;
    private int sizeAsInt;
    private double sizeAsDouble;
    private String contactEmail;
    private Date date;
    private String patternField;
    private String someImplementationField;

    private Integer version;

    private Set<Gadget> gadgets = new HashSet<Gadget>();

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public String getAnotherProperty()
    {
        return anotherProperty;
    }

    public void setAnotherProperty( String anotherProperty )
    {
        this.anotherProperty = anotherProperty;
    }

    @Email
    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail( String contactEmail )
    {
        this.contactEmail = contactEmail;
    }

    @Hidden("CREATE")
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    @Order( 1 )
    @EditorType( "long-string" )
    @Required
    @Length( min = 25 )
    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }


    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Gadget.class)
    public Set<Gadget> getGadgets()
    {
        return gadgets;
    }

    public void setGadgets(Set<Gadget> gadgets)
    {
        this.gadgets = gadgets;
    }

    @DisplayName( "Widget Name" )
    @Order( 0 )
    //@Required({"CREATE", "UPDATE"})
    @Length( min = 5, max = 25 )
    @Hidden("UPDATE")
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @DisplayName("My Weird Field")
    @Pattern("\\d+")
    public String getPatternField()
    {
        return patternField;
    }

    public void setPatternField(String patternField)
    {
        this.patternField = patternField;
    }

    @DoubleRange( max = 100.0 )
    public double getSizeAsDouble()
    {
        return sizeAsDouble;
    }

    public void setSizeAsDouble( double sizeAsDouble )
    {
        this.sizeAsDouble = sizeAsDouble;
    }

    @LongRange( min = -25 )
    public int getSizeAsInt()
    {
        return sizeAsInt;
    }

    public void setSizeAsInt( int sizeAsInt )
    {
        this.sizeAsInt = sizeAsInt;
    }

    @Ignored
    public String getSomeImplementationField()
    {
        return someImplementationField;
    }

    public void setSomeImplementationField(String someImplementationField)
    {
        this.someImplementationField = someImplementationField;
    }

    @Version
    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    @Required
    public WidgetType getWidgetType()
    {
        return widgetType;
    }

    public void setWidgetType( WidgetType widgetType )
    {
        this.widgetType = widgetType;
    }
}
