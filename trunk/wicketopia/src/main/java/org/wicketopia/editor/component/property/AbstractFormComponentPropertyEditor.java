/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.editor.component.property;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.IValidator;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.EditorBuilder;
import org.wicketopia.metadata.WicketopiaPropertyFacet;
import org.wicketopia.model.label.DisplayNameModel;

/**
 * @since 1.0
 */
public abstract class AbstractFormComponentPropertyEditor extends Panel implements EditorBuilder
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
        formComponent.setLabel(new DisplayNameModel(WicketopiaPropertyFacet.get(propertyMetaData)));
        add(formComponent);
    }

//----------------------------------------------------------------------------------------------------------------------
// EditorBuilder Implementation
//----------------------------------------------------------------------------------------------------------------------

    public void addBehavior(IBehavior behavior)
    {
        formComponent.add(behavior);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addValidator(IValidator validator)
    {
        formComponent.add(validator);
    }

    @Override
    public void enabled(boolean enabled)
    {
        formComponent.setEnabled(enabled);
    }

    public Component build()
    {
        return this;
    }

    @Override
    public void required(boolean required)
    {
        formComponent.setRequired(required);
    }

    @Override
    public void visible(boolean visible)
    {
        formComponent.setVisible(visible);
    }

    @Override
    public boolean isVisible()
    {
        return formComponent.isVisible();
    }
}
