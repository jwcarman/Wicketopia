package org.wicketopia.example.domain.entity;

import org.domdrides.entity.UuidEntity;
import org.wicketopia.annotation.metadata.DisplayName;
import org.wicketopia.annotation.metadata.EditorType;
import org.wicketopia.annotation.metadata.Order;

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
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
}
