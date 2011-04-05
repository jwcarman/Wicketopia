package org.wicketopia.model.label;

import org.apache.wicket.model.Model;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestPluralizedModel
{
    boolean detached = false;

    @Test
    public void testWithSingular()
    {
        Model<String> singularModel = new Model<String>("Person");
        final PluralizedModel model = new PluralizedModel(singularModel);
        assertEquals(model.getObject(), "Persons");
    }

    @Test
    public void testDetach()
    {
        Model<String> singularModel = new Model<String>("Person")
        {
            @Override
            public void detach()
            {
                detached = true;
            }
        };
        final PluralizedModel model = new PluralizedModel(singularModel);
        model.getObject();
        model.detach();
        assertTrue(detached );
    }

}
