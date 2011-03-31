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

import org.domdrides.entity.UuidEntity;
import org.hibernate.annotations.IndexColumn;
import org.wicketopia.builder.feature.annotation.metadata.DisplayName;
import org.wicketopia.builder.feature.annotation.metadata.EditorType;
import org.wicketopia.builder.feature.annotation.metadata.Ignored;
import org.wicketopia.builder.feature.annotation.metadata.Order;
import org.wicketopia.builder.feature.annotation.required.Required;
import org.wicketopia.builder.feature.annotation.validator.LongRange;
import org.wicketopia.builder.feature.annotation.validator.Pattern;
import org.wicketopia.builder.feature.annotation.visible.Hidden;
import org.wicketopia.context.Context;
import org.wicketopia.editor.component.property.TextAreaPropertyEditor;
import org.wicketopia.example.domain.value.WidgetType;
import org.wicketopia.builder.feature.annotation.validator.DoubleRange;
import org.wicketopia.builder.feature.annotation.validator.Email;
import org.wicketopia.builder.feature.annotation.validator.Length;
import org.wicketopia.spring.security.annotation.VisibleForRole;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    private String adminProperty;
    
    private Integer version;

    private List<Gadget> gadgets = new LinkedList<Gadget>();

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    @VisibleForRole("ROLE_ADMIN")
    public String getAdminProperty()
    {
        return adminProperty;
    }

    public void setAdminProperty(String adminProperty)
    {
        this.adminProperty = adminProperty;
    }

    public String getAnotherProperty()
    {
        return anotherProperty;
    }

    public void setAnotherProperty(String anotherProperty)
    {
        this.anotherProperty = anotherProperty;
    }

    @Email
    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    @Hidden({"CREATE", "LIST"})
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Order(1)
    @EditorType(TextAreaPropertyEditor.TYPE_NAME)
    @Required
    @Length(min = 25)
    @Hidden(Context.LIST)
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Gadget.class)
    @IndexColumn(name = "GADGET_INDEX")
    public List<Gadget> getGadgets()
    {
        return gadgets;
    }

    public void setGadgets(List<Gadget> gadgets)
    {
        this.gadgets = gadgets;
    }

    @DisplayName("Widget Name")
    @Order(0)
    //@Required({"CREATE", "UPDATE"})
    @Length(min = 5, max = 25)
    @Hidden("UPDATE")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
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

    @DoubleRange(max = 100.0)
    @Hidden(Context.LIST)
    public double getSizeAsDouble()
    {
        return sizeAsDouble;
    }

    public void setSizeAsDouble(double sizeAsDouble)
    {
        this.sizeAsDouble = sizeAsDouble;
    }

    @LongRange(min = -25)
    @Hidden(Context.LIST)
    public int getSizeAsInt()
    {
        return sizeAsInt;
    }

    public void setSizeAsInt(int sizeAsInt)
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

    @Required()
    public WidgetType getWidgetType()
    {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType)
    {
        this.widgetType = widgetType;
    }
}
