/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.util;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.metastopheles.PropertyMetaData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wicketopia.Wicketopia;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaPropertyFacet;
import org.wicketopia.persistence.filecfg.decorator.FileCfgBeanDecorator;
import org.wicketopia.persistence.filecfg.decorator.FileCfgPropertyDecorator;

/**
 * @author James Carman
 */
public class TestFileCfg extends AbstractWicketTestCase {

	public static class Entity implements Serializable {
		private static final long serialVersionUID = 1L;
		private String hiddenField;
		private String disabledField;
		private String ignoredField;
		private String field1;
		private String field2;
		private String field3;

		public String getHiddenField() {
			return hiddenField;
		}

		public void setHiddenField(String hiddenField) {
			this.hiddenField = hiddenField;
		}

		public String getDisabledField() {
			return disabledField;
		}

		public void setDisabledField(String disabledField) {
			this.disabledField = disabledField;
		}

		public String getIgnoredField() {
			return ignoredField;
		}

		public void setIgnoredField(String ignoredField) {
			this.ignoredField = ignoredField;
		}

		public String getField1() {
			return field1;
		}

		public void setField1(String field1) {
			this.field1 = field1;
		}

		public String getField2() {
			return field2;
		}

		public void setField2(String field2) {
			this.field2 = field2;
		}

		public String getField3() {
			return field3;
		}

		public void setField3(String field3) {
			this.field3 = field3;
		}

	}

	public static class TestPage extends WebPage {
		private static final long serialVersionUID = 1L;

		public TestPage(PropertyMetaData propertyMetadata) {
			add(new PropertyLabel("label", propertyMetadata));
		}
	}

	private Wicketopia wicketopiaPlugin;

	@BeforeMethod
	public void installWicketopia() {
		wicketopiaPlugin = new Wicketopia();
		wicketopiaPlugin.addBeanMetaDataDecorator(new FileCfgBeanDecorator());
		wicketopiaPlugin
				.addPropertyMetaDataDecorator(new FileCfgPropertyDecorator());
		wicketopiaPlugin.install(WebApplication.get());
	}

	// ----------------------------------------------------------------------------------------------------------------------
	// Other Methods
	// ----------------------------------------------------------------------------------------------------------------------

	@Test
	public void testOther() {
		final Context createContext = new Context(Context.CREATE);
		final Context updateContext = new Context(Context.UPDATE);
		final Context listContext = new Context(Context.LIST);

		PropertyMetaData hiddenMetadata = wicketopiaPlugin.getBeanMetaData(
				Entity.class).getPropertyMetaData("hiddenField");
		WicketopiaPropertyFacet facetHidden = WicketopiaPropertyFacet
				.get(hiddenMetadata);
		Assert.assertFalse(facetHidden.isVisible(createContext));
		Assert.assertTrue(facetHidden.isVisible(listContext));
		Assert.assertFalse(facetHidden.isEnabled(listContext));

		PropertyMetaData disabledMetadata = wicketopiaPlugin.getBeanMetaData(
				Entity.class).getPropertyMetaData("disabledField");
		WicketopiaPropertyFacet facetDisabled = WicketopiaPropertyFacet
				.get(disabledMetadata);
		Assert.assertFalse(facetDisabled.isEnabled(updateContext));
		Assert.assertTrue(facetDisabled.isEnabled(listContext));

		PropertyMetaData ignoredMetadata = wicketopiaPlugin.getBeanMetaData(
				Entity.class).getPropertyMetaData("ignoredField");
		WicketopiaPropertyFacet facetIgnored = WicketopiaPropertyFacet
				.get(ignoredMetadata);
		Assert.assertTrue(facetIgnored.isIgnored());

		PropertyMetaData field1Metadata = wicketopiaPlugin.getBeanMetaData(
				Entity.class).getPropertyMetaData("field1");
		WicketopiaPropertyFacet facetField1 = WicketopiaPropertyFacet
				.get(field1Metadata);
		Assert.assertEquals(facetField1.getOrder(), 3);

		PropertyMetaData field2Metadata = wicketopiaPlugin.getBeanMetaData(
				Entity.class).getPropertyMetaData("field2");
		WicketopiaPropertyFacet facetField2 = WicketopiaPropertyFacet
				.get(field2Metadata);
		Assert.assertEquals(facetField2.getOrder(), 2);

		PropertyMetaData field3Metadata = wicketopiaPlugin.getBeanMetaData(
				Entity.class).getPropertyMetaData("field3");
		WicketopiaPropertyFacet facetField3 = WicketopiaPropertyFacet
				.get(field3Metadata);
		Assert.assertEquals(facetField3.getOrder(), 1);
		Assert.assertFalse(facetField3.isVisible(createContext));
		Assert.assertTrue(facetField3.isVisible(listContext));
		Assert.assertFalse(facetField3.isEnabled(listContext));
	}
}
