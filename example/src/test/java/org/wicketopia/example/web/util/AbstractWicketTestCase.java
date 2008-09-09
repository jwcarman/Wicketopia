package org.wicketopia.example.web.util;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.wicketopia.example.web.application.WicketApplication;

public abstract class AbstractWicketTestCase
{
    protected AnnotApplicationContextMock springContext;
    protected WicketTester tester;
    protected Mockery mockery;

    @BeforeMethod
    public void constructWicketTester()
    {
        mockery = new Mockery();
        springContext = new AnnotApplicationContextMock();
        final WicketApplication application = new WicketApplication();
        application.setApplicationContext(springContext);
        tester = new WicketTester(application);
    }

    @AfterMethod
    public void assertMockeryIsSatisfied()
    {
        mockery.assertIsSatisfied();
    }
}
