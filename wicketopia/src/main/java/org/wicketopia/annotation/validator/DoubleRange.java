package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.PropertyMetadataModifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author James Carman
 */
@PropertyMetadataModifier( DoubleRangeHandler.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface DoubleRange
{
    double min() default Double.MIN_VALUE;

    double max() default Double.MAX_VALUE;
}
