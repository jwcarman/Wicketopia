package ${package}.web.application;

import ${package}.web.page.HomePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.ISpringContextLocator;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication implements ISpringContextLocator, ApplicationContextAware
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = -6044515824643215562L;
    private String configurationType = DEVELOPMENT;
    private ApplicationContext applicationContext;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public WicketApplication()
    {
    }

//**********************************************************************************************************************
// ApplicationContextAware Implementation
//**********************************************************************************************************************

    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

//**********************************************************************************************************************
// ISpringContextLocator Implementation
//**********************************************************************************************************************

    public ApplicationContext getSpringContext()
    {
        return applicationContext;
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    @Override
    public String getConfigurationType()
    {
        return configurationType;
    }

    public void setConfigurationType( String configurationType )
    {
        this.configurationType = configurationType;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    @Override
    public Class<HomePage> getHomePage()
    {
        return HomePage.class;
    }

    protected void init()
    {
        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this, getSpringContext()));
    }
}
