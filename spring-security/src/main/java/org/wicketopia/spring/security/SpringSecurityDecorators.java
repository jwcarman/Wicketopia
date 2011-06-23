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

package org.wicketopia.spring.security;

import org.metastopheles.annotation.ScanMe;
import org.wicketopia.security.decorator.AbstractSecurityDecorators;

/**
 * @since 1.0
 */
@ScanMe
public class SpringSecurityDecorators extends AbstractSecurityDecorators
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public SpringSecurityDecorators()
    {
        super(new SpringSecurityProvider());
    }
}