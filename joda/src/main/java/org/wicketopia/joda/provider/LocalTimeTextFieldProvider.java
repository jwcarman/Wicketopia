package org.wicketopia.joda.provider;

import org.apache.wicket.model.IModel;
import org.joda.time.LocalTime;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.joda.component.LocalTimeTextField;
import org.wicketopia.joda.util.FormatProvider;
import org.wicketopia.joda.util.StyleFormatProvider;

/**
 * @since 1.0
 */
public class LocalTimeTextFieldProvider implements PropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String TYPE_NAME = "joda-local-time";
    private FormatProvider defaultFormatProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public LocalTimeTextFieldProvider()
    {
        this(new StyleFormatProvider("-S"));
    }

    public LocalTimeTextFieldProvider(FormatProvider defaultFormatProvider)
    {
        this.defaultFormatProvider = defaultFormatProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public EditorBuilder createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        FormatProvider specifiedFormatProvider = propertyMetadata.getFacet(FormatProvider.FACET_KEY);
        
        return new TextFieldPropertyEditor(componentId, propertyMetadata, new LocalTimeTextField(TextFieldPropertyEditor.TEXT_FIELD_ID, (IModel<LocalTime>)propertyModel, specifiedFormatProvider == null ? defaultFormatProvider : specifiedFormatProvider));
    }
}
