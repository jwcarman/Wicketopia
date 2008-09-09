package org.wicketopia.example.domain.entity;

import org.domdrides.entity.UuidEntity;
import org.wicketopia.annotation.metadata.DisplayName;
import org.wicketopia.annotation.metadata.EditorType;
import org.wicketopia.annotation.metadata.Order;
import org.wicketopia.annotation.validator.Required;
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

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public String getAnotherProperty()
    {
        return anotherProperty;
    }

    public void setAnotherProperty( String anotherProperty )
    {
        this.anotherProperty = anotherProperty;
    }

    @Order( 1 )
    @EditorType( "long-string" )
    @Required
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
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public double getSizeAsDouble()
    {
        return sizeAsDouble;
    }

    public void setSizeAsDouble( double sizeAsDouble )
    {
        this.sizeAsDouble = sizeAsDouble;
    }

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
