package org.wicketopia.component.choice;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.wicketopia.renderer.EnumChoiceRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A useful {@link DropDownChoice} implementation for enum classes.  The values displayed for the enums can be
 * internationalized.  For enum value Bar from enum class com.myco.Foo, it will look for message key com.myco.Foo.Bar.
 * 
 * @since 1.0
 */
public class EnumDropDownChoice<T extends Enum> extends DropDownChoice<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 6199988612173580363L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    /**
     * Creates a drop down choice which contains <b>all</b> enum values (obtained via
     * the {@link Class#getEnumConstants()} method).
     * @param id the id of the component
     * @param enumClass the enum class
     */
    public EnumDropDownChoice( String id, Class<T> enumClass )
    {
        super(id, new ArrayList<T>(Arrays.asList(enumClass.getEnumConstants())));
        setChoiceRenderer(new EnumChoiceRenderer<T>(this));
    }

    /**
     * Creates a drop down choice which contains only the specified enum values.
     * @param id the id of the component
     * @param model the model
     * @param choices the choices
     */
    public EnumDropDownChoice( String id, IModel<T> model, List<? extends T> choices )
    {
        super(id, model, choices);
        setChoiceRenderer(new EnumChoiceRenderer<T>(this));
    }

    /**
     * Creates a drop down choice which contains <b>all</b> enum values (obtained via
     * the {@link Class#getEnumConstants()} method).
     * @param id the id of the component
     * @param model the model
     * @param enumClass the enum class
     */
    public EnumDropDownChoice( String id, IModel<T> model, Class<T> enumClass )
    {
        super(id, model, new ArrayList<T>(Arrays.asList(enumClass.getEnumConstants())));
        setChoiceRenderer(new EnumChoiceRenderer<T>(this));
    }
}
