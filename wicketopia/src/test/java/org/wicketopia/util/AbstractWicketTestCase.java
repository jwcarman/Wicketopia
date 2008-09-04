package org.wicketopia.util;

import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * @author James Carman
 */
public abstract class AbstractWicketTestCase
{
    protected WicketTester tester;
    protected Mockery mockery;

    @BeforeMethod
    public void constructWicketTester()
    {
        mockery = new Mockery();
        tester = new WicketTester();
    }

    @AfterMethod
    public void assertMockeryIsSatisfied()
    {
        mockery.assertIsSatisfied();
    }
}

