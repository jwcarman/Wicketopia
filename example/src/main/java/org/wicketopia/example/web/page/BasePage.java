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

package org.wicketopia.example.web.page;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BasePage extends WebPage implements IHeaderContributor
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public BasePage()
    {
        init();
    }

    private void init()
    {
        add(new Label("titleLabel", getTitleModel()).setRenderBodyOnly(true));
        add(new Label("captionLabel", getCaptionModel()).setRenderBodyOnly(true));
        add(new Label("copyrightLabel", resourceModel("page.copyright", new GregorianCalendar().get(
                Calendar.YEAR))).setEscapeModelStrings(false));
        add(new BookmarkablePageLink<Void>("homeLink", HomePage.class));
        add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
    }

    public BasePage(IModel<?> model)
    {
        super(model);
        init();
    }

    public BasePage(IPageMap pageMap)
    {
        super(pageMap);
        init();
    }

    public BasePage(PageParameters parameters)
    {
        super(parameters);
        init();
    }

    public BasePage(IPageMap pageMap, IModel<?> model)
    {
        super(pageMap, model);
        init();
    }

    public BasePage(IPageMap pageMap, PageParameters parameters)
    {
        super(pageMap, parameters);
        init();
    }

//----------------------------------------------------------------------------------------------------------------------
// IHeaderContributor Implementation
//----------------------------------------------------------------------------------------------------------------------

    public void renderHead(IHeaderResponse header)
    {
        header.renderCSSReference(new ResourceReference(BasePage.class, "style.css"));
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Returns a model which can be used to set the page's caption.  This implementation
     * merely returns a {@link org.apache.wicket.model.ResourceModel} which corresponds to the "page.caption" localized
     * string for this page.
     *
     * @return a model which can be used to set the page's caption
     */
    protected IModel<String> getCaptionModel()
    {
        return resourceModel("page.caption");
    }

    /**
     * Returns a model which can be used to set the page's title.  This implementation
     * merely returns a {@link org.apache.wicket.model.ResourceModel} which corresponds to the "page.title" localized
     * string for this page.
     *
     * @return a model which can be used to set the page's title
     */
    protected IModel<String> getTitleModel()
    {
        return resourceModel("page.title");
    }

    /**
     * Creates a resource model which corresponds to this page's <code>key</code> localized
     * resource string.
     *
     * @param key    the resource string's key
     * @param params the optional parameters
     * @return a resource model which corresponds to this page's <code>key</code> localized
     *         resource string
     */
    protected IModel<String> resourceModel(String key, Object... params)
    {
        if (params == null || params.length == 0)
        {
            return new ResourceModel(key, "[" + key + "]");
        }
        else
        {
            return new StringResourceModel(key, this, null, params, "[" + key + "]");
        }
    }
}
