package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.PropertyMetadataModifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author James Carman
 */
@PropertyMetadataModifier( LongRangeHandler.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface LongRange
{
    long min() default Long.MIN_VALUE;

    long max() default Long.MAX_VALUE;
}
