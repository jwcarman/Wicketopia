package org.wicketopia.example.web.page;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.IModel;

public class BasePage extends WebPage implements IHeaderContributor
{
//**********************************************************************************************************************
// Fields
//**********************************************************************************************************************

    private static final long serialVersionUID = 1L;

//**********************************************************************************************************************
// Constructors
//**********************************************************************************************************************

    public BasePage()
    {
    }

    public BasePage(IModel<?> model)
    {
        super(model);
    }

    public BasePage(IPageMap pageMap)
    {
        super(pageMap);
    }

    public BasePage(PageParameters parameters)
    {
        super(parameters);
    }

    public BasePage(IPageMap pageMap, IModel<?> model)
    {
        super(pageMap, model);
    }

    public BasePage(IPageMap pageMap, PageParameters parameters)
    {
        super(pageMap, parameters);
    }

//**********************************************************************************************************************
// IHeaderContributor Implementation
//**********************************************************************************************************************


    public void renderHead(IHeaderResponse header)
    {
        header.renderCSSReference(new ResourceReference(getClass(), "style.css"));
    }
}
