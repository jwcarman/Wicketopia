package org.wicketopia.example.web.page;

import org.jmock.Expectations;
import org.testng.annotations.Test;
import org.wicketopia.editor.def.DefaultEditorTypeMapping;
import org.wicketopia.editor.def.DefaultPropertyEditorFactory;
import org.wicketopia.example.domain.entity.Widget;
import org.wicketopia.example.domain.repository.WidgetRepository;
import org.wicketopia.example.web.util.AbstractWicketTestCase;

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
                one(widgetRepository).size();
                will(returnValue(0));
                one(widgetRepository).list(0, 0, "name", true);
                will(returnValue(Collections.<Widget>emptyList()));
            }
        });
        springContext.putBean("widgetRepository", widgetRepository);

        final DefaultPropertyEditorFactory factory = new DefaultPropertyEditorFactory();
        factory.setEditorTypeMapping(new DefaultEditorTypeMapping());
        springContext.putBean("propertyEditorFactory", factory);
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }
}
