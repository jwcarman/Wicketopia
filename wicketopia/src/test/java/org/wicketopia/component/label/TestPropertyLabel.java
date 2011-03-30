/*
 * Copyright (c) 2011 Carman Consulting, Inc.
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

package org.wicketopia.component.label;

import org.metastopheles.BeanMetaData;
import org.metastopheles.BeanMetaDataFactory;
import org.metastopheles.annotation.AnnotationBeanMetaDataFactory;
import org.testng.annotations.Test;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.Person;

/**
 * @author James Carman
 */
public class TestPropertyLabel extends AbstractWicketTestCase
{
    @Test
    public void testWithMessageKey()
    {
        final BeanMetaDataFactory factory = new AnnotationBeanMetaDataFactory();
        final BeanMetaData beanMetaData = factory.getBeanMetaData(Person.class);
        final PropertyLabelTestPage page = new PropertyLabelTestPage(beanMetaData.getPropertyMetaData("last"));
        tester.startPage(page);

        tester.assertLabel("label", "Last Name (i18n)");
    }

    @Test
    public void testWithoutMessageKey()
    {
        final BeanMetaDataFactory factory = new AnnotationBeanMetaDataFactory();
        final BeanMetaData beanMetaData = factory.getBeanMetaData(Person.class);
        final PropertyLabelTestPage page = new PropertyLabelTestPage(beanMetaData.getPropertyMetaData("first"));
        tester.startPage(page);
        tester.assertLabel("label", "First");
    }

    @Test
    public void testWithoutMessageKeyMultiWord()
    {
        final BeanMetaDataFactory factory = new AnnotationBeanMetaDataFactory();
        final BeanMetaData beanMetaData = factory.getBeanMetaData(Person.class);
        final PropertyLabelTestPage page =
                new PropertyLabelTestPage(beanMetaData.getPropertyMetaData("multiWordProperty"));
        tester.startPage(page);
        tester.assertLabel("label", "Multi Word Property");
    }
}
