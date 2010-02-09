package org.wicketopia.editor;

import org.metastopheles.PropertyMetaData;

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
     * @param propertyMetadata the property metadata
     * @return the editor type
     */
    public String getEditorType(PropertyMetaData propertyMetadata);
}
