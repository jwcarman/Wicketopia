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

package org.wicketopia.editor;

import org.metastopheles.PropertyMetaData;

/**
 * Maintains a mapping between property types and their default editor types.
 *
 * @since 1.0
 */
public interface EditorTypeMapping
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the editor type for the property type
     *
     * @param propertyMetadata the property metadata
     * @return the editor type
     */
    public String getEditorType(PropertyMetaData propertyMetadata);
}
