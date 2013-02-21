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

package org.wicketopia.util;

import org.apache.wicket.WicketRuntimeException;

import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

public final class ServiceLocator
{
//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static <T> T find(Class<T> type)
    {
        List<T> services = findAll(type);
        switch (services.size())
        {
            case 0:
                throw new WicketRuntimeException("There are no services of type " + type.getName()+ " registered.");
            case 1:
                return services.get(0);
            default:
                throw new WicketRuntimeException("There are more than one services of type " + type.getName() + " registered.");
        }
    }

    public static <T> List<T> findAll(Class<T> type)
    {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(type);
        final List<T> services = new LinkedList<T>();
        for (T t : serviceLoader)
        {
            services.add(t);
        }
        return services;
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    private ServiceLocator()
    {
        
    }
}
