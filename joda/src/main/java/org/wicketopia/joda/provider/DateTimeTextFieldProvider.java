package org.wicketopia.joda.provider;

import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.joda.component.DateTimeTextField;
import org.wicketopia.joda.util.FormatProvider;
import org.wicketopia.joda.util.StyleFormatProvider;

/**
 * @since 1.0
 */
public class DateTimeTextFieldProvider implements PropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String TYPE_NAME = "joda-date-time";
    private FormatProvider defaultFormatProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public DateTimeTextFieldProvider()
    {
        this(new StyleFormatProvider("SS"));
    }

    public DateTimeTextFieldProvider(FormatProvider defaultFormatProvider)
    {
        this.defaultFormatProvider = defaultFormatProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public EditorBuilder createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        FormatProvider specifiedFormatProvider = propertyMetadata.getFacet(FormatProvider.FACET_KEY);

        return new TextFieldPropertyEditor(componentId, propertyMetadata, new DateTimeTextField(TextFieldPropertyEditor.TEXT_FIELD_ID, (IModel<DateTime>) propertyModel, specifiedFormatProvider == null ? defaultFormatProvider : specifiedFormatProvider));
    }
}
