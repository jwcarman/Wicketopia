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

package org.wicketopia.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.wicketopia.security.SecurityProvider;

import java.util.Set;

/**
 * @since 1.0
 */
public class ShiroSecurityProvider implements SecurityProvider {
//----------------------------------------------------------------------------------------------------------------------
// SecurityProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean checkRoles(Set<String> roles) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            for (String role : roles) {
                if (subject.hasRole(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
