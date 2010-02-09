/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.component.choice;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketopia.util.Gender;
import org.wicketopia.util.Person;

/**
 * @since 1.0
 */
public class EnumDropDownChoiceTestPage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public EnumDropDownChoiceTestPage(Person p)
    {
        Form<Person> form = new Form<Person>("form", new CompoundPropertyModel<Person>(p));
        form.add(new EnumDropDownChoice<Gender>("gender", Gender.class));
        add(form);
    }
}
