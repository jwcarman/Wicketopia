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

package org.wicketopia.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wicketopia.security.SecurityProvider;

import java.util.Set;

/**
 * @since 1.0
 */
public class SpringSecurityProvider implements SecurityProvider {
//----------------------------------------------------------------------------------------------------------------------
// SecurityProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean checkRoles(Set<String> roles) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null ||
                securityContext.getAuthentication() == null ||
                securityContext.getAuthentication().getAuthorities() == null) {
            return false;
        }

        for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
            if (roles.contains(authority.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
