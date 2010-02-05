package org.wicketopia.example.domain.entity;

import java.util.Date;

import org.domdrides.entity.UuidEntity;
import org.wicketopia.annotation.metadata.DisplayName;
import org.wicketopia.annotation.metadata.EditorType;
import org.wicketopia.annotation.metadata.Order;
import org.wicketopia.annotation.validator.*;
import org.wicketopia.example.domain.value.WidgetType;

import javax.persistence.Entity;

@Entity
public class Widget extends UuidEntity
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

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

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************


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

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

    @DisplayName( "Widget Name" )
    @Order( 0 )
    @Required
    @Length( min = 5, max = 25 )
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
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
