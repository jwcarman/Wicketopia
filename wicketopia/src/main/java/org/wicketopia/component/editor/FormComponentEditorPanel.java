/*
 * Copyright (c) 2010 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import org.metastopheles.PropertyMetaData;
import org.wicketopia.component.choice.EnumDropDownChoice;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.model.label.PropertyLabelModel;

/**
 * @since 1.0
 */
public class FormComponentEditorPanel extends Panel implements PropertyEditor
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

    private final FormComponent formComponent;

//**********************************************************************************************************************
// Static Methods
//**********************************************************************************************************************

    public static FormComponentEditorPanel createEnumChoicePanel(String id, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        final EnumDropDownChoice<?> choice =
                new EnumDropDownChoice("choice", propertyModel, propertyMetadata.getPropertyDescriptor().getPropertyType());
        choice.setLabel(new PropertyLabelModel(propertyMetadata));
        return new FormComponentEditorPanel(id, "enumDdcEditor", choice);
    }

    public static FormComponentEditorPanel createTextAreaPanel(String id, PropertyMetaData propertyMetadata, IModel<?> propertyModel)
    {
        final TextArea formComponent = new TextArea("editor", propertyModel);
        formComponent.setLabel(new PropertyLabelModel(propertyMetadata));
        return new FormComponentEditorPanel(id, "textAreaEditor", formComponent);
    }

    public static FormComponentEditorPanel createTextFieldPanel(String id,
            PropertyMetaData propertyMetadata,
            IModel<?> propertyModel)
    {
        final TextField formComponent = new TextField("editor", propertyModel, propertyMetadata.getPropertyDescriptor().getPropertyType());
        if (propertyMetadata.getPropertyDescriptor().getPropertyType().isPrimitive())
        {
            formComponent.setRequired(true);
        }
        formComponent.setLabel(new PropertyLabelModel(propertyMetadata));
        return new FormComponentEditorPanel(id, "textFieldEditor", formComponent);
    }

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    private FormComponentEditorPanel(String id, String fragmentId, FormComponent formComponent)
    {
        super(id);
        this.formComponent = formComponent;
        final Fragment fragment = new Fragment("fragment", fragmentId, this);
        fragment.add(formComponent);
        fragment.setRenderBodyOnly(true);
        add(fragment);
        setRenderBodyOnly(true);
    }

//**********************************************************************************************************************
// PropertyEditor Implementation
//**********************************************************************************************************************

    public void addBehavior(IBehavior behavior)
    {
        formComponent.add(behavior);
    }

    public void addValidator(IValidator validator)
    {
        formComponent.add(validator);
    }

    public Component getEditorComponent()
    {
        return this;
    }

    public void setRequired(boolean required)
    {
        formComponent.setRequired(true);
    }
}
