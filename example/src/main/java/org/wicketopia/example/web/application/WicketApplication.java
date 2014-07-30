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
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.wicketopia.Wicketopia;
import org.wicketopia.example.web.page.HomePage;
import org.wicketopia.example.web.page.bean.BeanEditorExample;
import org.wicketopia.example.web.page.bean.BeanViewerExample;
import org.wicketopia.example.web.page.custom.CustomBeanEditorExample;
import org.wicketopia.example.web.page.custom.viewer.ImageBooleanViewer;
import org.wicketopia.example.web.page.list.BeanListEditorExample;
import org.wicketopia.example.web.page.list.BeanListViewerExample;
import org.wicketopia.example.web.page.scaffold.ScaffoldExample;
import org.wicketopia.example.web.page.table.BeanTableExample;
import org.wicketopia.listener.ajax.AutoFeedbackListener;
import org.wicketopia.persistence.PersistencePlugin;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.hibernate.decorator.HibernatePropertyDecorator;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 */
@Component("wicketApplication")
public class WicketApplication extends WebApplication {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = -6044515824643215562L;

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Autowired
    private PersistenceProvider persistenceProvider;

    public WicketApplication() {
    }

//----------------------------------------------------------------------------------------------------------------------
// ApplicationContextAware Implementation
//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    @Value("${wicket.configuration}")
    public void setConfigurationType(RuntimeConfigurationType configurationType) {
        super.setConfigurationType(configurationType);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------------
    // Other Methods
    // ----------------------------------------------------------------------------------------------------------------------

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    protected void init() {
        super.init();
        mountPage("/examples/scaffold", ScaffoldExample.class);
        mountPage("/examples/beanViewer", BeanViewerExample.class);
        mountPage("/examples/beanEditor", BeanEditorExample.class);
        mountPage("/examples/beanListViewer", BeanListViewerExample.class);
        mountPage("/examples/beanListEditor", BeanListEditorExample.class);
        mountPage("/examples/customBeanEditor", CustomBeanEditorExample.class);
        mountPage("/examples/beanTable", BeanTableExample.class);

        Wicketopia wicketopia = new Wicketopia();
        wicketopia.addPlugin(new PersistencePlugin(persistenceProvider));
        wicketopia.addPropertyMetaDataDecorator(new HibernatePropertyDecorator(new PropertyModel<Configuration>(sessionFactoryBean, "configuration")));
        wicketopia.addPropertyViewerProvider("image-boolean", ImageBooleanViewer.getProvider());

        wicketopia.install(this);
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this));
        getAjaxRequestTargetListeners().add(new AutoFeedbackListener());
    }
}
