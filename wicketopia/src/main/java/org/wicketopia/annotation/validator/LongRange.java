package org.wicketopia.annotation.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author James Carman
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface LongRange
{
    long min() default Long.MIN_VALUE;

    long max() default Long.MAX_VALUE;
}
