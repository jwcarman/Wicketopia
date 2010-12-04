/*
 * Copyright (c) 2010 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    public void testModelContentsWithEnumClassParameter()
    {
        EnumDropDownChoice<Gender> genderChoice = new EnumDropDownChoice<Gender>("gender", Gender.class);
        List<? extends Gender> choices = genderChoice.getChoices();
        assertEquals(choices, Arrays.asList(Gender.class.getEnumConstants()));

        genderChoice = new EnumDropDownChoice<Gender>("gender", Gender.Female, Gender.Male, Gender.Female);
        choices = genderChoice.getChoices();
        assertEquals(choices, Arrays.asList(Gender.Female, Gender.Male, Gender.Female));
    }

    @Test
    @SuppressWarnings( "unchecked" )
    public void testRendering()
    {
        final EnumDropDownChoiceTestPage page = new EnumDropDownChoiceTestPage(new Person());
        tester.startPage(page);
        tester.assertRenderedPage(EnumDropDownChoiceTestPage.class);
        EnumDropDownChoice<Gender> choice =
                ( EnumDropDownChoice<Gender> ) tester.getComponentFromLastRenderedPage("form:gender");
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
        assertEquals(p.getGender(), Gender.Female);
    }
}
