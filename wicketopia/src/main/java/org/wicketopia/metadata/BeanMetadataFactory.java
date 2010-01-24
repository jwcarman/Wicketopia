package org.wicketopia.metadata;

public interface BeanMetadataFactory
{
    public <T> BeanMetadata<T> getBeanMetadata(Class<T> beanClass); 
}
