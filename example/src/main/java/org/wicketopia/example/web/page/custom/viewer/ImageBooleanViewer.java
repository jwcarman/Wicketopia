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

package org.wicketopia.example.web.page.custom.viewer;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.builder.ViewerBuilder;
import org.wicketopia.context.Context;
import org.wicketopia.viewer.PropertyViewerProvider;

public class ImageBooleanViewer extends Panel implements ViewerBuilder
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static PropertyViewerProvider provider = new Provider();
    private final Image image;

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static PropertyViewerProvider getProvider()
    {
        return provider;
    }

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ImageBooleanViewer(String id, IModel<?> model)
    {
        super(id);
        image = new Image("image", new ImageResourceModel(model));
        add(image);
    }

//----------------------------------------------------------------------------------------------------------------------
// ComponentBuilder Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void addBehavior(Behavior behavior)
    {
        image.add(behavior);
    }

    @Override
    public Component build()
    {
        return this;
    }

    @Override
    public void visible(boolean viewable)
    {
        image.setVisible(viewable);
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private static final class ImageResourceModel extends LoadableDetachableModel<ResourceReference>
    {
        private final IModel<?> inner;

        private ImageResourceModel(IModel<?> inner)
        {
            this.inner = inner;
        }

        @Override
        protected ResourceReference load()
        {
            if (Boolean.TRUE.equals(inner.getObject()))
            {
            	return new PackageResourceReference(ImageBooleanViewer.class, "images/green-check-mark.png");
            }
            else
            {
            	return new PackageResourceReference(ImageBooleanViewer.class, "images/red-x-mark.png");
            }
        }
    }

    private static final class Provider implements PropertyViewerProvider
    {
        @Override
        public ViewerBuilder createPropertyViewer(String componentId, PropertyMetaData propertyMetadata, IModel<?> propertyModel, Context context)
        {
            return new ImageBooleanViewer(componentId, propertyModel);
        }
    }
}
