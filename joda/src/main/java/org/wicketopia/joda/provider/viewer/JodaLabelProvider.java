package org.wicketopia.joda.provider.viewer;

import org.apache.wicket.model.IModel;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.joda.component.viewer.JodaLabel;
import org.wicketopia.joda.util.format.FormatProvider;
import org.wicketopia.joda.util.format.JodaFormatSupport;
import org.wicketopia.viewer.PropertyViewerProvider;
import org.wicketopia.viewer.component.LabelPropertyViewer;

/**
 * @author James Carman
 */
public class JodaLabelProvider<T> implements PropertyViewerProvider
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final JodaFormatSupport<T> formatSupport;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JodaLabelProvider(JodaFormatSupport<T> formatSupport)
    {
        this.formatSupport = formatSupport;
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyViewerProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public ViewerBuilder createPropertyViewer(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        final FormatProvider specifiedFormatProvider = propertyMetadata.getFacet(FormatProvider.FACET_KEY);
        return new JodaLabel<T>(componentId, (IModel<T>) propertyModel, specifiedFormatProvider == null ? formatSupport : formatSupport.withProvider(specifiedFormatProvider));
    }


}
