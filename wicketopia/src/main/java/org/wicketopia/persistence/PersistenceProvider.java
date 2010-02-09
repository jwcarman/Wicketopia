package org.wicketopia.persistence;

public interface PersistenceProvider
{
    public <T> T create(T object);

    public <T> void delete(T object);

    public <T> T update(T object);
}
