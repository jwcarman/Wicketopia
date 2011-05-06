package org.wicketopia.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wicketopia.security.SecurityProvider;

import java.util.Set;

/**
 * @since 1.0
 */
public class SpringSecurityProvider implements SecurityProvider
{
//----------------------------------------------------------------------------------------------------------------------
// SecurityProvider Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean checkRoles(Set<String> roles)
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null ||
                securityContext.getAuthentication() == null ||
                securityContext.getAuthentication().getAuthorities() == null)
        {
            return false;
        }

        for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities())
        {
            if(roles.contains(authority.getAuthority()))
            {
                return true;
            }
        }
        return false;
    }
}
