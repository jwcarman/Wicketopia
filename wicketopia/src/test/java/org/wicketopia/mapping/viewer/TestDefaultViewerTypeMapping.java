package org.wicketopia.mapping.viewer;

import org.testng.annotations.Test;
import org.wicketopia.editor.component.property.EnumDropDownChoicePropertyEditor;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.mapping.editor.DefaultEditorTypeMapping;
import org.wicketopia.util.Gender;
import org.wicketopia.viewer.component.LabelPropertyViewer;

import static org.testng.Assert.assertEquals;

/**
 * @author James Carman
 */
public class TestDefaultViewerTypeMapping
{
    @Test
    public void testDefaultMappings()
    {
        final DefaultViewerTypeMapping mapping = new DefaultViewerTypeMapping();
        assertEquals(mapping.getTypeName(String.class), LabelPropertyViewer.TYPE_NAME);
        assertEquals(mapping.getTypeName(Gender.class), LabelPropertyViewer.TYPE_NAME);
    }
}
