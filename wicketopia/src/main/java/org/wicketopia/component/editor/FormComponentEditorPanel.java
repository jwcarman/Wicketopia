package org.wicketopia.component.editor;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidator;
import org.wicketopia.editor.PropertyEditorBuilder;

/**
 * @since 1.0
 */
public class FormComponentEditorPanel extends Panel implements PropertyEditorBuilder
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

    private final FormComponent formComponent;

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static FormComponentEditorPanel createTextFieldPanel( String id, IModel<?> propertyModel )
    {
        final TextField formComponent = new TextField("editor", propertyModel);
        return new FormComponentEditorPanel(id, "textFieldEditor", formComponent);
    }

    public static FormComponentEditorPanel createTextAreaPanel( String id, IModel<?> propertyModel )
    {
        final TextArea formComponent = new TextArea("editor", propertyModel);
        return new FormComponentEditorPanel(id, "textAreaEditor", formComponent);
    }

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    private FormComponentEditorPanel( String id, String fragmentId, FormComponent formComponent )
    {
        super(id);
        this.formComponent = formComponent;
        final Fragment fragment = new Fragment("fragment", fragmentId, this);
        fragment.add(formComponent);
        add(fragment);
    }

//**********************************************************************************************************************
// PropertyEditorBuilder Implementation
//**********************************************************************************************************************


    public void addBehavior( IBehavior behavior )
    {
        formComponent.add(behavior);
    }

    public void addValidator( IValidator validator )
    {
        formComponent.add(validator);
    }

    public Component buildPropertyEditor()
    {
        return this;
    }

    public void setRequired( boolean required )
    {
        formComponent.setRequired(true);
    }
}
