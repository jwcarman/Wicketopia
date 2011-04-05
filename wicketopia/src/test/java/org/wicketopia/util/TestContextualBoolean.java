package org.wicketopia.util;

import org.testng.annotations.Test;
import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;

import static org.testng.Assert.*;

public class TestContextualBoolean
{
    @Test
    public void testWithFalseDefaultValue()
    {
        final ContextualBoolean cb = new ContextualBoolean(false);
        final Context context = new Context(Context.CREATE);
        assertFalse(cb.getValue(context));
        cb.setValue(Context.whereContextNameIn(Context.CREATE), true);
        assertTrue(cb.getValue(context));
        cb.setValue(Context.whereContextNameIn(Context.CREATE), false);
        assertTrue(cb.getValue(context));
    }

    @Test
    public void testWithTrueDefaultValue()
    {
        final ContextualBoolean cb = new ContextualBoolean(true);
        final Context context = new Context(Context.CREATE);
        assertTrue(cb.getValue(context));
        cb.setValue(Context.whereContextNameIn(Context.CREATE), false);
        assertFalse(cb.getValue(context));
        cb.setValue(Context.whereContextNameIn(Context.CREATE), true);
        assertFalse(cb.getValue(context));
    }
}
