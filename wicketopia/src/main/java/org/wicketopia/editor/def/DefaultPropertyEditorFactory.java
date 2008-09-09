package org.wicketopia.editor.def;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.wicketopia.editor.EditorTypeMapping;
import org.wicketopia.editor.PropertyEditorBuilder;
import org.wicketopia.editor.PropertyEditorBuilderFactory;
import org.wicketopia.editor.PropertyEditorFacet;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.metadata.PropertyMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 1.0
 */
public class DefaultPropertyEditorFactory implements PropertyEditorFactory
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private EditorTypeMapping editorTypeMapping;
    private Map<String, PropertyEditorBuilderFactory> builderFactoryMap =
            new HashMap<String, PropertyEditorBuilderFactory>();

//**********************************************************************************************************************
// PropertyEditorFactory Implementation
//**********************************************************************************************************************

    public Component createPropertyEditor( String id, PropertyMetadata propertyMetadata, IModel<?> propertyModel )
    {
        String editorType = propertyMetadata.getEditorType();
        if( editorType == null )
        {
            editorType = editorTypeMapping.getEditorType(propertyMetadata.getPropertyDescriptor().getPropertyType());
        }
        if( editorType == null )
        {
            throw new IllegalArgumentException("Unable to determine editor type for property " +
                    propertyMetadata.getPropertyDescriptor().getName() + " of class " +
                    propertyMetadata.getBeanMetadata().getBeanClass().getName() + ".");
        }
        PropertyEditorBuilderFactory builderFactory = builderFactoryMap.get(editorType);
        if( builderFactory == null )
        {
            throw new IllegalArgumentException(
                    "No property editor builder defined for editor type \"" + editorType + ".\"");
        }
        PropertyEditorBuilder builder = builderFactory.createBuilder(id, propertyModel);
        for( PropertyEditorFacet facet : propertyMetadata.getFacets() )
        {
            facet.apply(builder);
        }
        return builder.buildPropertyEditor();
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public void setEditorTypeMapping( EditorTypeMapping editorTypeMapping )
    {
        this.editorTypeMapping = editorTypeMapping;
    }

//**********************************************************************************************************************
// Other Methods
//**********************************************************************************************************************

    public void addBuilderFactoryOverride( String editorType, PropertyEditorBuilderFactory builderFactory )
    {
        builderFactoryMap.put(editorType, builderFactory);
    }

    public void setBuilderFactoryOverrides( Map<String, PropertyEditorBuilderFactory> builderFactoryOverrides )
    {
        for( String editorType : builderFactoryOverrides.keySet() )
        {
            builderFactoryMap.put(editorType, builderFactoryOverrides.get(editorType));
        }
    }
}
