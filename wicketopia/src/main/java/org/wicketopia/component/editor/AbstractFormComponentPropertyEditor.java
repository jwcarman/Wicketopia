/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.IValidator;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.model.label.PropertyLabelModel;

/**
 * @since 1.0
 */
public abstract class AbstractFormComponentPropertyEditor extends Panel implements PropertyEditor
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final FormComponent<?> formComponent;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractFormComponentPropertyEditor(String id, PropertyMetaData propertyMetaData, FormComponent<?> formComponent)
    {
        super(id);
        this.formComponent = formComponent;
        formComponent.setLabel(new PropertyLabelModel(propertyMetaData));
        setRenderBodyOnly(true);
        add(formComponent);
    }

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditor Implementation
//----------------------------------------------------------------------------------------------------------------------

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
        formComponent.setRequired(required);
    }
}
