/*
 * Copyright (c) 2010 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.editor.facet;

import org.apache.wicket.validation.IValidator;
import org.wicketopia.editor.EditorContext;
import org.wicketopia.editor.PropertyEditor;
import org.wicketopia.editor.PropertyEditorFacet;

/**
 * @since 1.0
 */
public abstract class AbstractValidatorFacet implements PropertyEditorFacet
{
//**********************************************************************************************************************
// Abstract Methods
//**********************************************************************************************************************

    protected abstract IValidator createValidator(EditorContext context);

//**********************************************************************************************************************
// PropertyEditorFacet Implementation
//**********************************************************************************************************************


    public final void apply(PropertyEditor builder, EditorContext context)
    {
        final IValidator validator = createValidator(context);
        if (validator != null)
        {
            builder.addValidator(validator);
        }
    }
}
