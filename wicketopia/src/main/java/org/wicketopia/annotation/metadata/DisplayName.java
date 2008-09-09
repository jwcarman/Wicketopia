package org.wicketopia.annotation.metadata;

import org.wicketopia.annotation.PropertyMetadataModifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since 1.0
 */
@PropertyMetadataModifier( DisplayNameHandler.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface DisplayName
{
    String value();
}
