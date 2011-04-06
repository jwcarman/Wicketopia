package org.wicketopia.joda.provider.editor;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.joda.component.editor.JodaTimeTextField;
import org.wicketopia.joda.util.format.FormatProvider;
import org.wicketopia.joda.util.format.StyleFormatProvider;
import org.wicketopia.joda.util.translator.DateTimeTranslator;

/**
 * @since 1.0
 */
public class JodaTimeTextFieldProvider<T> implements PropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private FormatProvider defaultFormatProvider;
    private DateTimeTranslator<T> translator;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTimeTextFieldProvider(DateTimeTranslator<T> translator, String defaultStyle)
    {
        this.defaultFormatProvider = new StyleFormatProvider(defaultStyle);
        this.translator = translator;
    }

    public JodaTimeTextFieldProvider(DateTimeTranslator<T> translator, FormatProvider defaultFormatProvider)
    {
        this.defaultFormatProvider = defaultFormatProvider;
        this.translator = translator;
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public final EditorBuilder createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        final FormatProvider specifiedFormatProvider = propertyMetadata.getFacet(FormatProvider.FACET_KEY);
        final JodaTimeTextField<T> field = new JodaTimeTextField<T>(TextFieldPropertyEditor.TEXT_FIELD_ID, (IModel<T>) propertyModel, specifiedFormatProvider == null ? defaultFormatProvider : specifiedFormatProvider, translator, (Class<T>) propertyMetadata.getPropertyDescriptor().getPropertyType());
        return new TextFieldPropertyEditor(componentId, propertyMetadata, field);
    }
}
