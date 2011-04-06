package org.wicketopia.joda.util.translator;

import org.apache.wicket.IClusterable;
import org.joda.time.DateTime;

/**
 * @since 1.0
 */
public interface DateTimeTranslator<T> extends IClusterable
{
    public DateTime toDateTime(T object);
    public T fromDateTime(DateTime dateTime);
}
