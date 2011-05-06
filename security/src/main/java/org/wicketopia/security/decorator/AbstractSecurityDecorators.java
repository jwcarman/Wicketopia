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

package org.wicketopia.security.decorator;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.metadata.WicketopiaPropertyFacet;
import org.wicketopia.security.SecurityProvider;
import org.wicketopia.security.annotation.*;
import org.wicketopia.security.predicate.RequiredRolesPredicate;

/**
 * @since 1.0
 */
public abstract class AbstractSecurityDecorators
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final SecurityProvider securityProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractSecurityDecorators(SecurityProvider securityProvider)
    {
        this.securityProvider = securityProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public  void onDisabledForRole(PropertyMetaData propertyMetaData, DisabledForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setEnabled(new RequiredRolesPredicate(securityProvider, annot.value()), false);
    }

    @PropertyDecorator
    public  void onEnabledForRole(PropertyMetaData propertyMetaData, EnabledForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setEnabled(new RequiredRolesPredicate(securityProvider, annot.value()), true);
    }

    @PropertyDecorator
    public  void onHiddenForRole(PropertyMetaData propertyMetaData, HiddenForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setVisible(new RequiredRolesPredicate(securityProvider, annot.value()), false);
    }
    
    @PropertyDecorator
    public  void onOptionalForRole(PropertyMetaData propertyMetaData, OptionalForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setRequired(new RequiredRolesPredicate(securityProvider, annot.value()), false);
    }

    @PropertyDecorator
    public  void onRequiredForRole(PropertyMetaData propertyMetaData, RequiredForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setRequired(new RequiredRolesPredicate(securityProvider, annot.value()), true);
    }

    @PropertyDecorator
    public  void onVisibleForRole(PropertyMetaData propertyMetaData, VisibleForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setVisible(new RequiredRolesPredicate(securityProvider, annot.value()), true);
    }
}
