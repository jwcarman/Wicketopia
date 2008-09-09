package org.wicketopia.component.form.rad;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;
import org.wicketopia.component.editor.PropertyEditorPanel;
import org.wicketopia.component.form.CreateEntityForm;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.editor.PropertyEditorFactory;
import org.wicketopia.metadata.BeanMetadataFactory;
import org.wicketopia.metadata.PropertyMetadata;

import java.io.Serializable;

/**
 * @author James Carman
 */
public abstract class RadCreateEntityForm<EntityType extends Entity<IdType>, IdType extends Serializable>
        extends CreateEntityForm<EntityType, IdType>
{
    private static final long serialVersionUID = 1L;

    public RadCreateEntityForm( String id, final PropertyEditorFactory propertyEditorFactory,
                                Repository<EntityType, IdType> repository )
    {
        super(id, repository);
        final ListView<PropertyMetadata> listView =
                new ListView<PropertyMetadata>("properties", BeanMetadataFactory.getInstance()
                        .getBeanMetadata(getEntityType()).getAllPropertyMetadata())
                {
                    private static final long serialVersionUID = 1L;

                    protected void populateItem( ListItem<PropertyMetadata> item )
                    {
                        PropertyMetadata propertyMetadata = item.getModelObject();
                        item.add(new PropertyLabel("label", propertyMetadata));
                        item.add(new PropertyEditorPanel("editor", propertyMetadata, new PropertyModel(
                                RadCreateEntityForm.this.getModel(), propertyMetadata.getPropertyName()),
                                                         propertyEditorFactory));
                    }
                };
        listView.setReuseItems(true);
        add(listView);
    }

}
