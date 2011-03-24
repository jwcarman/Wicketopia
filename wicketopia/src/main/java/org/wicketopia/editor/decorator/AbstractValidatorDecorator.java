/*
 * Copyright (c) 2011 Carman Consulting, Inc.
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

package org.wicketopia.editor.decorator;

import org.apache.wicket.validation.IValidator;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorDecorator;

/**
 * @since 1.0
 */
public abstract class AbstractValidatorDecorator implements PropertyEditorDecorator
{
//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    protected abstract IValidator<?> createValidator(EditorContext context);

//----------------------------------------------------------------------------------------------------------------------
// PropertyEditorDecorator Implementation
//----------------------------------------------------------------------------------------------------------------------

    public final void apply(PropertyEditor editor, EditorContext context)
    {
        final IValidator<?> validator = createValidator(context);
        if (validator != null)
        {
            editor.addValidator(validator);
        }
    }
}
