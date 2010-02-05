package org.wicketopia.persistence;

public interface PersistenceService
{
    public <T> T create(T object);

    public <T> void delete(T object);

    public <T> T update(T object);
}
