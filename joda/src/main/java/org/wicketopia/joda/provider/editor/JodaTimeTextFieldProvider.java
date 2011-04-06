package org.wicketopia.joda.provider.editor;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.joda.component.editor.JodaTimeTextField;
import org.wicketopia.joda.util.format.DateTimeFormatSupport;
import org.wicketopia.joda.util.format.FormatProvider;

/**
 * @since 1.0
 */
public class JodaTimeTextFieldProvider<T> implements PropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private DateTimeFormatSupport<T> formatSupport;


//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTimeTextFieldProvider(DateTimeFormatSupport<T> formatSupport)
    {
        this.formatSupport = formatSupport;
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public final EditorBuilder createPropertyEditor(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        final FormatProvider specifiedFormatProvider = propertyMetadata.getFacet(FormatProvider.FACET_KEY);
        final JodaTimeTextField<T> field = new JodaTimeTextField<T>(TextFieldPropertyEditor.TEXT_FIELD_ID, (IModel<T>) propertyModel, specifiedFormatProvider == null ? formatSupport : formatSupport.withProvider(specifiedFormatProvider), (Class<T>) propertyMetadata.getPropertyDescriptor().getPropertyType());
        return new TextFieldPropertyEditor(componentId, propertyMetadata, field);
    }
}
