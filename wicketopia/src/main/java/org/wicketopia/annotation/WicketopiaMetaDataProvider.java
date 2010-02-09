package org.wicketopia.annotation;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.MetaDataProvider;
import org.wicketopia.annotation.metadata.DisplayName;
import org.wicketopia.annotation.metadata.EditorType;
import org.wicketopia.annotation.metadata.Order;
import org.wicketopia.annotation.validator.CreditCard;
import org.wicketopia.annotation.validator.DoubleRange;
import org.wicketopia.annotation.validator.Email;
import org.wicketopia.annotation.validator.Length;
import org.wicketopia.annotation.validator.LongRange;
import org.wicketopia.annotation.validator.Pattern;
import org.wicketopia.annotation.validator.Required;
import org.wicketopia.editor.facet.validator.CreditCardFacet;
import org.wicketopia.editor.facet.validator.DoubleRangeFacet;
import org.wicketopia.editor.facet.validator.EmailFacet;
import org.wicketopia.editor.facet.validator.LengthFacet;
import org.wicketopia.editor.facet.validator.LongRangeFacet;
import org.wicketopia.editor.facet.validator.PatternFacet;
import org.wicketopia.editor.facet.validator.RequiredFacet;
import org.wicketopia.metadata.WicketopiaPropertyMetaData;

/**
 * @author James Carman
 */
@MetaDataProvider
public class WicketopiaMetaDataProvider
{
    public void onCreditCard(PropertyMetaData propertyMetaData, CreditCard creditCard)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(new CreditCardFacet());
    }

    public void onDoubleRange(PropertyMetaData propertyMetaData, DoubleRange doubleRange)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(new DoubleRangeFacet(doubleRange.min(), doubleRange.max()));
    }

    public void onEmail(PropertyMetaData propertyMetaData, Email email)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(new EmailFacet());
    }

    public void onLength(PropertyMetaData propertyMetaData, Length length)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(new LengthFacet(length.min(), length.max()));
    }

    public void onLongRange(PropertyMetaData propertyMetaData, LongRange longRange)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(new LongRangeFacet(longRange.min(), longRange.max()));
    }

    public void onPattern(PropertyMetaData propertyMetaData, Pattern pattern)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(new PatternFacet(pattern.value()));
    }

    public void onRequired(PropertyMetaData propertyMetaData, Required required)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).addFacet(RequiredFacet.getInstance());
    }

    public void onDisplayName(PropertyMetaData propertyMetaData, DisplayName displayName)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).setDefaultLabelText(displayName.value());
    }

    public void onEditorType(PropertyMetaData propertyMetaData, EditorType editorType)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).setEditorType(editorType.value());
    }

    public void onOrder(PropertyMetaData propertyMetaData, Order order)
    {
        WicketopiaPropertyMetaData.get(propertyMetaData).setOrder(order.value());
    }
}
