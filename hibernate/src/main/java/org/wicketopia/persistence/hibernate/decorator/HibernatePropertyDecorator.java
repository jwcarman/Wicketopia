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

package org.wicketopia.persistence.hibernate.decorator;

import org.apache.wicket.model.IModel;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.SimpleValue;
import org.metastopheles.BeanMetaData;
import org.metastopheles.MetaDataDecorator;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.context.Context;
import org.wicketopia.metadata.WicketopiaPropertyFacet;

import java.util.Iterator;

public class HibernatePropertyDecorator implements MetaDataDecorator<PropertyMetaData> {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final IModel<Configuration> configuration;

    private boolean ignoreIdentifiers = true;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public HibernatePropertyDecorator(IModel<Configuration> configuration) {
        this.configuration = configuration;
    }

//----------------------------------------------------------------------------------------------------------------------
// MetaDataDecorator Implementation
//----------------------------------------------------------------------------------------------------------------------

    public void decorate(PropertyMetaData propertyMetaData) {
        WicketopiaPropertyFacet facet = WicketopiaPropertyFacet.get(propertyMetaData);
        final PersistentClass classMapping = findClassMapping(propertyMetaData.getBeanMetaData());
        if (classMapping != null) {
            final Property property = classMapping.getProperty(propertyMetaData.getPropertyDescriptor().getName());

            if (property == null) {
                return;
            } else if (!(property.getValue() instanceof SimpleValue)) {
                facet.setIgnored(true);
            } else if (ignoreIdentifiers && property.equals(classMapping.getIdentifierProperty())) {
                facet.setIgnored(true);
            } else if (property.equals(classMapping.getVersion())) {
                facet.setIgnored(true);
            }

            if (!property.isOptional()) {
                facet.setRequired(Context.ALL_CONTEXTS, true);
            }
        }
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private PersistentClass findClassMapping(BeanMetaData beanMetaData) {
        for (Iterator i = configuration.getObject().getClassMappings(); i.hasNext(); ) {
            PersistentClass classMapping = (PersistentClass) i.next();
            if (classMapping.getMappedClass().equals(beanMetaData.getBeanDescriptor().getBeanClass())) {
                return classMapping;
            }
        }
        return null;
    }

    public HibernatePropertyDecorator ignoreIdentifiers(boolean flag) {
        this.ignoreIdentifiers = flag;
        return this;
    }
}
