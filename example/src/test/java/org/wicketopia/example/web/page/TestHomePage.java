package org.wicketopia.example.web.page;

import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.syntax.MethodClause;
import org.testng.annotations.Test;
import org.wicketopia.WicketopiaPlugin;
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
                atLeast(1).of(widgetRepository).size();
                will(returnValue(0));
            }
        });
        springContext.putBean("widgetRepository", widgetRepository);
        new WicketopiaPlugin().install(tester.getApplication());
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }
}
