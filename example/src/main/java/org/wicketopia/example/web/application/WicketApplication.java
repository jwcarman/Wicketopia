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

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.BufferedResponseMapper;
import org.apache.wicket.spring.ISpringContextLocator;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.cfg.Configuration;
import org.jboss.weld.environment.servlet.Listener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.wicketopia.Wicketopia;
import org.wicketopia.cdi.CdiPlugin;
import org.wicketopia.example.web.page.HomePage;
import org.wicketopia.example.web.page.custom.viewer.ImageBooleanViewer;
import org.wicketopia.listener.ajax.AutoFeedbackListener;
import org.wicketopia.persistence.hibernate.decorator.HibernatePropertyDecorator;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 */
@Component("wicketApplication")
public class WicketApplication extends WebApplication implements
        ISpringContextLocator, ApplicationContextAware
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------------------------------------------------------
    private static final long serialVersionUID = -6044515824643215562L;

    private RuntimeConfigurationType configurationType;
    private ApplicationContext applicationContext;

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;


    public WicketApplication()
    {
    }

//----------------------------------------------------------------------------------------------------------------------
// ApplicationContextAware Implementation
//----------------------------------------------------------------------------------------------------------------------


    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
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
    public RuntimeConfigurationType getConfigurationType()
    {
        return configurationType;
    }

    @Value("${wicket.configuration}")
    public void setConfigurationType(RuntimeConfigurationType configurationType)
    {
        this.configurationType = configurationType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------------
    // Other Methods
    // ----------------------------------------------------------------------------------------------------------------------

    @Override
    public Class<HomePage> getHomePage()
    {
        return HomePage.class;
    }

    protected void init()
    {
        super.init();
        Wicketopia wicketopia = new Wicketopia();
        wicketopia.addPropertyMetaDataDecorator(new HibernatePropertyDecorator(new PropertyModel<Configuration>(sessionFactoryBean, "configuration")));
        wicketopia.addPropertyViewerProvider("image-boolean", ImageBooleanViewer.getProvider());

        wicketopia.install(this);
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, getSpringContext(), true));
        getAjaxRequestTargetListeners().add(new AutoFeedbackListener());
        mount(new BufferedResponseMapper()
        {
            protected String getSessionId()
            {
                return Session.get().getId();
            }
        });
    }
}
