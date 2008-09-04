package ${package}.web.util;

import ${package}.web.application.WicketApplication;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
