package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.PropertyMetadataModifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author James Carman
 */
@PropertyMetadataModifier( LengthHandler.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface Length
{
    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;
}
