package ${package}.web.page;

import ${package}.domain.entity.Widget;
import ${package}.domain.repository.WidgetRepository;
import ${package}.web.util.AbstractWicketTestCase;
import org.jmock.Expectations;
import org.testng.annotations.Test;

import java.util.Collections;

public class TestHomePage extends AbstractWicketTestCase
{
    @Test
    public void testRenderMyPage()
	{
        final WidgetRepository widgetRepository = mockery.mock(WidgetRepository.class);
        mockery.checking(new Expectations()
        {
            {
                one(widgetRepository).size(); will(returnValue(0));
                one(widgetRepository).list(0,0,"name",true); will(returnValue(Collections.<Widget>emptyList()));
            }
        });
        springContext.putBean("widgetRepository", widgetRepository);

        tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
	}
}
