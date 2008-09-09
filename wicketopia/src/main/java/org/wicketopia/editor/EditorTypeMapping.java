package org.wicketopia.editor;

/**
 * Maintains a mapping between property types and their default editor types.
 *
 * @since 1.0
 */
public interface EditorTypeMapping
{
    /**
     * Returns the editor type for the property type
     *
     * @param propertyType the property type
     * @return the editor type
     */
    public String getEditorType( Class propertyType );
}
