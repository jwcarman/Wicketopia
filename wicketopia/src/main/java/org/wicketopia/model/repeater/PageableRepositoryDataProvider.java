package org.wicketopia.model.repeater;

import org.domdrides.entity.Entity;
import org.domdrides.repository.PageableRepository;
import org.domdrides.wicket.model.LoadableDetachableEntityModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;

import java.io.Serializable;
import java.util.Iterator;

/**
 * A {@link org.domdrides.repository.PageableRepository}-based implementation of {@link org.apache.wicket.markup.repeater.data.IDataProvider}.  Each @{Entity} returned by this
 * data provider will be wrapped with a {@link org.domdrides.wicket.model.LoadableDetachableEntityModel}.
 *
 * @since 1.1
 */
public class PageableRepositoryDataProvider<EntityType extends Entity<IdType>, IdType extends Serializable> extends
        SortableDataProvider<EntityType>
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;
    private final PageableRepository<EntityType, IdType> repository;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    /**
     * Constructs a data provider which returns records from the specified {@link PageableRepository} sorted by the
     * specified sort property (ascending)
     * @param repository the repository
     * @param sortProperty the sort property
     */
    public PageableRepositoryDataProvider(PageableRepository<EntityType, IdType> repository, String sortProperty)
    {
        this.repository = repository;
        setSort(new SortParam(sortProperty, true));
    }

    /**
     * Constructs a data provider which returns records from the specified {@link PageableRepository} sorted by the
     * specified sort property.
     * @param repository the repository
     * @param sortProperty the sort property
     * @param ascending whether or not to sort ascending
     */
    public PageableRepositoryDataProvider(PageableRepository<EntityType, IdType> repository, String sortProperty, boolean ascending)
    {
        this.repository = repository;
        setSort(new SortParam(sortProperty, ascending));
    }

//**********************************************************************************************************************
// IDataProvider Implementation
//**********************************************************************************************************************

    public Iterator<? extends EntityType> iterator(int first, int max)
    {
        return repository.list(first, max, getSort().getProperty(), getSort().isAscending()).iterator();
    }

    public IModel<EntityType> model(EntityType entity)
    {
        return new LoadableDetachableEntityModel<EntityType,IdType>(repository, entity);
    }

    public int size()
    {
        return repository.size();
    }
}
