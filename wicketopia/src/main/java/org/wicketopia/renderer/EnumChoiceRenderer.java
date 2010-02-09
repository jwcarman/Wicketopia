package org.wicketopia.renderer;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.StringResourceModel;

/**
 * A choice renderer for enum values which allows internationalization of the display values.  For enum value Bar from
 * enum class com.myco.Foo, it will look for message key com.myco.Foo.Bar.
 *
 * @since 1.0
 */
public class EnumChoiceRenderer<T extends Enum> implements IChoiceRenderer<T>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = -2873650216055558416L;
    private final DropDownChoice<T> dropDownChoice;

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    private static <T extends Enum> String getEnumDisplayValue(T enumValue, DropDownChoice<T> dropDownChoice)
    {
        return new StringResourceModel(enumValue.getClass().getName() + "." + enumValue.name(), dropDownChoice, null, enumValue.toString()).getString();
    }

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public EnumChoiceRenderer(DropDownChoice<T> dropDownChoice)
    {
        this.dropDownChoice = dropDownChoice;
    }

//**********************************************************************************************************************
// IChoiceRenderer Implementation
//**********************************************************************************************************************


    public Object getDisplayValue(T enumValue)
    {
        return getEnumDisplayValue(enumValue, dropDownChoice);
    }

    public String getIdValue(T enumValue, int index)
    {
        return String.valueOf(enumValue.ordinal());
    }
}
