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

package org.wicketopia.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class CdiUtils
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Map<CacheKey<?>, WeakReference<? extends InjectionTarget<?>>> injectionTargetCache = new WeakHashMap<CacheKey<?>, WeakReference<? extends InjectionTarget<?>>>();

    private static final ReadWriteLock cacheLock = new ReentrantReadWriteLock();

    private static final Logger logger = LoggerFactory.getLogger(CdiUtils.class);

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    static void clearCache()
    {
        try
        {
            cacheLock.writeLock().lock();
            injectionTargetCache.clear();
        }
        finally
        {
            cacheLock.writeLock().unlock();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getBeanType(T object)
    {
        return (Class<T>) object.getClass();
    }

    @SuppressWarnings("unchecked")
    private static <T> InjectionTarget<T> getInjectionTarget(T object, BeanManager beanManager)
    {
        final Class<T> objectType = getBeanType(object);

        final CacheKey<T> key = new CacheKey<T>(objectType, beanManager);
        WeakReference<InjectionTarget<T>> injectionTargetRef;
        try
        {
            cacheLock.readLock().lock();
            injectionTargetRef = (WeakReference<InjectionTarget<T>>) injectionTargetCache.get(key);
        }
        finally
        {
            cacheLock.readLock().unlock();
        }

        InjectionTarget<T> injectionTarget = injectionTargetRef == null ? null : injectionTargetRef.get();
        if (injectionTarget == null)
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("No injection target found for {} creating one...", objectType.getName());
            }
            try
            {
                cacheLock.writeLock().lock();
                AnnotatedType<T> type = beanManager.createAnnotatedType(objectType);
                injectionTarget = beanManager.createInjectionTarget(type);
                injectionTargetRef = new WeakReference<InjectionTarget<T>>(injectionTarget);
                injectionTargetCache.put(key, injectionTargetRef);
            }
            finally
            {
                cacheLock.writeLock().unlock();
            }
        }
        return injectionTarget;
    }

    public static <T> void inject(T object, BeanManager beanManager)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("Injecting object {}...", object);
        }
        CreationalContext<T> creationalContext = beanManager.createCreationalContext(null);
        getInjectionTarget(object, beanManager).inject(object, creationalContext);
    }

    public static <T> void postConstruct(T object, BeanManager beanManager)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("Post-constructing {}...", object);
        }
        getInjectionTarget(object, beanManager).postConstruct(object);
    }

    public static <T> void preDestroy(T object, BeanManager beanManager)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace("Pre-destroying {}...", object);
        }
        getInjectionTarget(object, beanManager).preDestroy(object);
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    private CdiUtils()
    {
        
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static final class CacheKey<T>
    {
        private final Class<T> beanClass;
        private final BeanManager beanManager;

        private CacheKey(Class<T> beanClass, BeanManager beanManager)
        {
            this.beanClass = beanClass;
            this.beanManager = beanManager;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            CacheKey cacheKey = (CacheKey) o;

            if (!beanClass.equals(cacheKey.beanClass))
            {
                return false;
            }
            if (!beanManager.equals(cacheKey.beanManager))
            {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = beanClass.hashCode();
            result = 31 * result + beanManager.hashCode();
            return result;
        }
    }
}
