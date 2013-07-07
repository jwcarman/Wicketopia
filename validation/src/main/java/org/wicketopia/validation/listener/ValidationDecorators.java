package org.wicketopia.validation.listener;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.annotation.validator.Pattern;
import org.wicketopia.builder.feature.validator.DoubleRangeFeature;
import org.wicketopia.builder.feature.validator.LengthFeature;
import org.wicketopia.builder.feature.validator.LongRangeFeature;
import org.wicketopia.builder.feature.validator.PatternFeature;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ValidationDecorators
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onMax(PropertyMetaData propertyMetaData, Max max)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new LongRangeFeature(Long.MIN_VALUE, max.value()));
    }

    @PropertyDecorator
    public static void onMax(PropertyMetaData propertyMetaData, DecimalMax max)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new DoubleRangeFeature(Double.MIN_VALUE, new BigDecimal(max.value()).doubleValue()));
    }

    @PropertyDecorator
    public static void onMin(PropertyMetaData propertyMetaData, Min min)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new LongRangeFeature(min.value(), Long.MAX_VALUE));
    }

    @PropertyDecorator
    public static void onMin(PropertyMetaData propertyMetaData, DecimalMin min)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new DoubleRangeFeature(new BigDecimal(min.value()).doubleValue(), Double.MAX_VALUE));
    }

    @PropertyDecorator
    public static void onNotNull(PropertyMetaData propertyMetaData, NotNull notNull)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setRequired(Context.ALL_CONTEXTS, true);
    }

    @PropertyDecorator
    public static void onPattern(PropertyMetaData propertyMetaData, Pattern pattern)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new PatternFeature(pattern.value()));
    }

    @PropertyDecorator
    public static void onSize(PropertyMetaData propertyMetaData, Size size)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).addEditorFeature(new LengthFeature(size.min(), size.max()));
    }
}
