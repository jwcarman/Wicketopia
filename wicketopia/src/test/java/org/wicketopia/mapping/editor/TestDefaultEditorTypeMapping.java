package org.wicketopia.mapping.editor;

import org.testng.annotations.Test;
import org.wicketopia.component.choice.EnumDropDownChoice;
import org.wicketopia.editor.component.property.EnumDropDownChoicePropertyEditor;
import org.wicketopia.editor.component.property.TextFieldPropertyEditor;
import org.wicketopia.util.Gender;

import static org.testng.Assert.*;
/**
 * @author James Carman
 */
public class TestDefaultEditorTypeMapping
{
    @Test
    public void testDefaultMappings()
    {
        final DefaultEditorTypeMapping mapping = new DefaultEditorTypeMapping();
        assertEquals(mapping.getTypeName(String.class), TextFieldPropertyEditor.TYPE_NAME);
        assertEquals(mapping.getTypeName(Gender.class), EnumDropDownChoicePropertyEditor.TYPE_NAME);
    }
}
