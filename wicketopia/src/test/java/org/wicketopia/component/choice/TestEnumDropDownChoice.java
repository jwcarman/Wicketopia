package org.wicketopia.component.choice;

import org.apache.wicket.util.tester.FormTester;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Gender;
import org.wicketopia.util.Person;

import java.util.Arrays;
import java.util.List;

/**
 * @author James Carman
 */
public class TestEnumDropDownChoice extends AbstractWicketTestCase
{
    @Test
    public void testModelContents()
    {
        final EnumDropDownChoice<Gender> genderChoice = new EnumDropDownChoice<Gender>("gender", Gender.class);
        List<? extends Gender> choices = genderChoice.getChoices();
        assertEquals(choices, Arrays.asList(Gender.class.getEnumConstants()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRendering()
    {
        final EnumDropDownChoiceTestPage page = new EnumDropDownChoiceTestPage(new Person());
        tester.startPage(page);
        tester.assertRenderedPage(EnumDropDownChoiceTestPage.class);
        EnumDropDownChoice<Gender> choice = ( EnumDropDownChoice<Gender>)tester.getComponentFromLastRenderedPage("form:gender");
        assertEquals(choice.getChoiceRenderer().getDisplayValue(Gender.Female), "Female");
        assertEquals(choice.getChoiceRenderer().getDisplayValue(Gender.Male), "Male");
        assertEquals(choice.getChoiceRenderer().getDisplayValue(Gender.Unknown), "It's Pat!");
    }

    @Test
    public void testModelBinding()
    {
        final Person p = new Person();
        final EnumDropDownChoiceTestPage page = new EnumDropDownChoiceTestPage(p);
        tester.startPage(page);
        FormTester formTester = tester.newFormTester("form");
        formTester.select("gender", Gender.Female.ordinal());
        formTester.submit();
        assertEquals( p.getGender(), Gender.Female );
    }
}
