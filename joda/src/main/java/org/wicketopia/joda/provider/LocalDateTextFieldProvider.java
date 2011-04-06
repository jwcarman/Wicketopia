package org.wicketopia.joda.provider;

import org.apache.wicket.model.IModel;
import org.joda.time.LocalDate;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.joda.component.LocalDateTextField;
import org.wicketopia.joda.util.FormatProvider;
import org.wicketopia.joda.util.StyleFormatProvider;

/**
 * @since 1.0
 */
public class LocalDateTextFieldProvider implements PropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String TYPE_NAME = "joda-local-date";
    private FormatProvider defaultFormatProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public LocalDateTextFieldProvider()
    {
        this(new StyleFormatProvider("S-"));
    }

    public LocalDateTextFieldProvider(FormatProvider defaultFormatProvider)
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
        
        return new TextFieldPropertyEditor(componentId, propertyMetadata, new LocalDateTextField(TextFieldPropertyEditor.TEXT_FIELD_ID, (IModel<LocalDate>)propertyModel, specifiedFormatProvider == null ? defaultFormatProvider : specifiedFormatProvider));
    }
}
