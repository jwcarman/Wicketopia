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

package org.wicketopia.security.predicate;

import org.wicketopia.context.Context;
import org.wicketopia.context.ContextPredicate;
import org.wicketopia.security.SecurityProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @since 1.0
 */
public class RequiredRolesPredicate implements ContextPredicate
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Set<String> roles;
    private final SecurityProvider securityProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public RequiredRolesPredicate(SecurityProvider securityProvider, String... roles)
    {
        this.roles = new HashSet<String>(Arrays.asList(roles));
        this.securityProvider = securityProvider;
    }

//----------------------------------------------------------------------------------------------------------------------
// ContextPredicate Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean evaluate(Context context)
    {
        return securityProvider.checkRoles(roles);
    }
}

