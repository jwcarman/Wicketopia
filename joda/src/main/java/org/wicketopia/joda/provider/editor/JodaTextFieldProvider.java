package org.wicketopia.joda.provider.editor;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.editor.PropertyEditorProvider;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.joda.component.editor.JodaTextField;
import org.wicketopia.joda.util.format.JodaFormatSupport;
import org.wicketopia.joda.util.format.FormatProvider;

/**
 * @since 1.0
 */
public class JodaTextFieldProvider<T> implements PropertyEditorProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private JodaFormatSupport<T> formatSupport;


//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaTextFieldProvider(JodaFormatSupport<T> formatSupport)
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
        final JodaTextField<T> field = new JodaTextField<T>(TextFieldPropertyEditor.TEXT_FIELD_ID, (IModel<T>) propertyModel, specifiedFormatProvider == null ? formatSupport : formatSupport.withProvider(specifiedFormatProvider), (Class<T>) propertyMetadata.getPropertyDescriptor().getPropertyType());
        return new TextFieldPropertyEditor(componentId, propertyMetadata, field);
    }
}
