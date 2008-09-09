package org.wicketopia.example.web.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.resources.StyleSheetReference;

public class BasePage extends WebPage
{
    private static final long serialVersionUID = 1L;

    public BasePage()
    {
        add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
    }
}
