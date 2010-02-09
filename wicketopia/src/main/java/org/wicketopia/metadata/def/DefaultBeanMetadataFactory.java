package org.wicketopia.metadata.def;

import org.wicketopia.editor.EditorTypeMapping;
import org.wicketopia.editor.def.DefaultEditorTypeMapping;
import org.wicketopia.metadata.*;
import org.wicketopia.metadata.decorator.PropertyMetadataModifierDecorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultBeanMetadataFactory implements BeanMetadataFactory
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private List<BeanMetadataDecorator> beanMetadataDecorators = new ArrayList<BeanMetadataDecorator>();
    private List<PropertyMetadataDecorator> propertyMetadataDecorators = new ArrayList<PropertyMetadataDecorator>();
    private final Map<Class<?>, BeanMetadata<?>> beanMetadataMap = new HashMap<Class<?>, BeanMetadata<?>>();
    private EditorTypeMapping editorTypeMapping = new DefaultEditorTypeMapping();

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public DefaultBeanMetadataFactory()
    {
        propertyMetadataDecorators.add(new PropertyMetadataModifierDecorator());
    }

//**********************************************************************************************************************
// BeanMetadataFactory Implementation
//**********************************************************************************************************************

    @SuppressWarnings("unchecked")
    public synchronized <T> BeanMetadata<T> getBeanMetadata(Class<T> beanClass)
    {
        BeanMetadata<T> beanMetadata = (BeanMetadata<T>) beanMetadataMap.get(beanClass);
        if (beanMetadata == null)
        {
            beanMetadata = new BeanMetadata<T>(beanClass);
            for (BeanMetadataDecorator decorator : beanMetadataDecorators)
            {
                decorator.decorate(beanMetadata);
            }
            for (WicketopiaPropertyMetaData propertyMetadata : beanMetadata.getAllPropertyMetadata())
            {
                for (PropertyMetadataDecorator decorator : propertyMetadataDecorators)
                {
                    decorator.decorate(propertyMetadata);
                }
                if(propertyMetadata.getEditorType() == null)
                {
                    propertyMetadata.setEditorType(editorTypeMapping.getEditorType(propertyMetadata));
                }
            }
            beanMetadataMap.put(beanClass, beanMetadata);
        }
        return beanMetadata;
    }

//**********************************************************************************************************************
// Getter/Setter Methods
//**********************************************************************************************************************

    public EditorTypeMapping getEditorTypeMapping()
    {
        return editorTypeMapping;
    }

    public void setEditorTypeMapping(EditorTypeMapping editorTypeMapping)
    {
        this.editorTypeMapping = editorTypeMapping;
    }

    public void setBeanMetadataDecorators(List<BeanMetadataDecorator> beanMetadataDecorators)
    {
        this.beanMetadataDecorators = beanMetadataDecorators;
    }

    public void setPropertyMetadataDecorators(List<PropertyMetadataDecorator> propertyMetadataDecorators)
    {
        this.propertyMetadataDecorators = propertyMetadataDecorators;
    }
}
