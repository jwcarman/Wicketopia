package org.wicketopia.layout.view;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.util.AbstractWicketTestCase;
import org.wicketopia.util.EditableBean;
import org.wicketopia.util.Gender;

/**
 * @author James Carman
 */
public class TestCssBeanViewLayoutPanel extends AbstractWicketTestCase
{
    @Test
    public void testWithViewer()
    {
        WicketopiaPlugin plugin = new WicketopiaPlugin(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        tester.startPage(new CssBeanViewLayoutTestPage(bean, plugin.createViewerFactory(EditableBean.class)));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Int Property");
        tester.assertModelValue("view:prop-div:2:prop-component", 123);
        tester.assertLabel("view:prop-div:3:prop-label", "Double Property");
        tester.assertModelValue("view:prop-div:3:prop-component", 456.0);
        tester.assertLabel("view:prop-div:4:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:4:prop-component", Gender.Male);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), CssBeanViewLayoutPanel.DEFAULT_CSS_CLASS);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testWithEditor()
    {
        WicketopiaPlugin plugin = new WicketopiaPlugin(tester.getApplication());
        final EditableBean bean = new EditableBean();
        bean.setStringProperty("Hello");
        bean.setIntProperty(123);
        bean.setDoubleProperty(456.0);
        bean.setGender(Gender.Male);
        tester.startPage(new CssBeanViewLayoutTestPage(bean, plugin.createEditorFactory(EditableBean.class)));
        tester.assertRenderedPage(CssBeanViewLayoutTestPage.class);
        tester.assertLabel("view:prop-div:1:prop-label", "String Property");
        tester.assertModelValue("view:prop-div:1:prop-component:editor", "Hello");
        tester.assertLabel("view:prop-div:2:prop-label", "Int Property");
        tester.assertModelValue("view:prop-div:2:prop-component:editor", 123);
        tester.assertLabel("view:prop-div:3:prop-label", "Double Property");
        tester.assertModelValue("view:prop-div:3:prop-component:editor", 456.0);
        tester.assertLabel("view:prop-div:4:prop-label", "Gender");
        tester.assertModelValue("view:prop-div:4:prop-component:ddc", Gender.Male);
        assertEquals(tester.getTagByWicketId("view").getAttribute("class"), CssBeanViewLayoutPanel.DEFAULT_CSS_CLASS);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }
}
