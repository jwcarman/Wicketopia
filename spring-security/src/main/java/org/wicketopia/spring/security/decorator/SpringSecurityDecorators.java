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

package org.wicketopia.spring.security.decorator;

import org.metastopheles.PropertyMetaData;
import org.metastopheles.annotation.PropertyDecorator;
import org.wicketopia.metadata.WicketopiaPropertyFacet;
import org.wicketopia.spring.security.annotation.DisabledForRole;
import org.wicketopia.spring.security.annotation.EnabledForRole;
import org.wicketopia.spring.security.annotation.HiddenForRole;
import org.wicketopia.spring.security.annotation.OptionalForRole;
import org.wicketopia.spring.security.annotation.RequiredForRole;
import org.wicketopia.spring.security.annotation.VisibleForRole;
import org.wicketopia.spring.security.predicate.RequiredRolesPredicate;

/**
 * @author James Carman
 */
public class SpringSecurityDecorators
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    @PropertyDecorator
    public static void onHiddenForRole(PropertyMetaData propertyMetaData, HiddenForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setVisible(new RequiredRolesPredicate(annot.value()), false);
    }

    @PropertyDecorator
    public static void onVisibleForRole(PropertyMetaData propertyMetaData, VisibleForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setVisible(new RequiredRolesPredicate(annot.value()), true);
    }

    @PropertyDecorator
    public static void onDisabledForRole(PropertyMetaData propertyMetaData, DisabledForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setEnabled(new RequiredRolesPredicate(annot.value()), false);
    }

    @PropertyDecorator
    public static void onEnabledForRole(PropertyMetaData propertyMetaData, EnabledForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setEnabled(new RequiredRolesPredicate(annot.value()), true);
    }
    
    @PropertyDecorator
    public static void onOptionalForRole(PropertyMetaData propertyMetaData, OptionalForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setRequired(new RequiredRolesPredicate(annot.value()), false);
    }

    @PropertyDecorator
    public static void onRequiredForRole(PropertyMetaData propertyMetaData, RequiredForRole annot)
    {
        WicketopiaPropertyFacet.get(propertyMetaData).setRequired(new RequiredRolesPredicate(annot.value()), true);
    }
}
