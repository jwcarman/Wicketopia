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

package org.wicketopia.example.web.application;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.ISpringContextLocator;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.example.web.page.HomePage;
import org.wicketopia.persistence.hibernate.decorator.HibernatePropertyDecorator;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
@Component("wicketApplication")
public class WicketApplication extends WebApplication implements ISpringContextLocator, ApplicationContextAware
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = -6044515824643215562L;
    private String configurationType = DEVELOPMENT;
    private ApplicationContext applicationContext;

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public WicketApplication()
    {
    }

//----------------------------------------------------------------------------------------------------------------------
// ApplicationContextAware Implementation
//----------------------------------------------------------------------------------------------------------------------

    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

//----------------------------------------------------------------------------------------------------------------------
// ISpringContextLocator Implementation
//----------------------------------------------------------------------------------------------------------------------

    public ApplicationContext getSpringContext()
    {
        return applicationContext;
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public String getConfigurationType()
    {
        return configurationType;
    }

    @Value("${wicket.configuration}")
    public void setConfigurationType( String configurationType )
    {
        this.configurationType = configurationType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public Class<HomePage> getHomePage()
    {
        return HomePage.class;
    }

    protected void init()
    {
        super.init();
        WicketopiaPlugin plugin = new WicketopiaPlugin(this);
        plugin.addPropertyMetaDataDecorator(new HibernatePropertyDecorator(sessionFactoryBean.getConfiguration()));
        addComponentInstantiationListener(new SpringComponentInjector(this, getSpringContext(), true));
    }
}
