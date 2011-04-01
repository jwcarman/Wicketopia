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

package org.wicketopia.example.web.page;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.component.scaffold.Scaffold;

import java.security.Security;

/**
 * Homepage
 */
public class HomePage extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PersistenceProvider persistenceProvider;

    @SpringBean
    private AuthenticationManager authenticationManager;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public HomePage()
    {
        add(new Scaffold<Person>("scaffold", Person.class, persistenceProvider));
        add(new Link("loginAdmin")
        {
            @Override
            public void onClick()
            {
                final UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken("admin", "admin");
                SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(tok));
                setResponsePage(HomePage.class);
                setRedirect(true);
            }

            @Override
            public boolean isVisible()
            {
                return SecurityContextHolder.getContext().getAuthentication() == null;
            }
        });

        add(new Link("logout")
        {
            @Override
            public void onClick()
            {
                SecurityContextHolder.clearContext();
                setResponsePage(HomePage.class);
                setRedirect(true);
            }

            @Override
            public boolean isVisible()
            {
                return SecurityContextHolder.getContext().getAuthentication() != null;
            }
        });
    }
}
