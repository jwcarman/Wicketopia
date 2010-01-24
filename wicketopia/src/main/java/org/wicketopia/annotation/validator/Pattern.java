package org.wicketopia.annotation.validator;

import org.wicketopia.annotation.PropertyMetadataModifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PropertyMetadataModifier( PatternHandler.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface Pattern
{
    String value();
}
