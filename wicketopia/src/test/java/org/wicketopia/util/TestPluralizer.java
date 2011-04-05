package org.wicketopia.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestPluralizer
{
    @Test
    public void testCommonWords()
    {
        assertPlural("Person", "Persons");
        assertPlural("Party", "Parties");
    }

    private void assertPlural(String singular, String plural)
    {
        assertEquals(Pluralizer.pluralize(singular), plural);
    }

}
