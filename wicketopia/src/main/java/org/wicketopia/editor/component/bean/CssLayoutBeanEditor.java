package org.wicketopia.editor.component.bean;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.wicketopia.editor.context.EditorContext;

/**
 * A simple layout that relies on CSS to layout the form elements.  The form will look like:
 *
 * <div class="css-bean-editor"><wicket:panel>
 *   <div class="prop-div">
 *      <div class="prop-label">Field Label</div>
 *      <div class="prop-editor"><input type="text" /></div>
 *   </div>
 * </div>
 *
 * <b>Note: The user must supply their own CSS to configure how they want the forms to look.</b>
 *
 * @since 1.0
 */
public class CssLayoutBeanEditor<T> extends AbstractBeanEditor<T>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final String DEFAULT_CSS_CLASS = "css-bean-editor";
    private String cssClass = DEFAULT_CSS_CLASS;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public CssLayoutBeanEditor(String id, Class<T> beanType, IModel<T> beanModel, EditorContext editorContext, String... properties)
    {
        super(id, beanType, beanModel, editorContext, properties);
        final RepeatingView view = new RepeatingView("prop-div");
        for (String propertyName : getPropertyNameList())
        {
            final Component editor = createPropertyEditor("prop-editor", propertyName);
            final WebMarkupContainer div = new WebMarkupContainer(view.newChildId())
            {
                @Override
                public boolean isVisible()
                {
                    return editor.isVisible();
                }
            };
            div.add(editor);
            div.add(createPropertyLabel("prop-label", propertyName));
            view.add(div);
        }
        add(view);
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onComponentTag(ComponentTag tag)
    {
        tag.getAttributes().put("class", cssClass);
    }

    public CssLayoutBeanEditor<T> setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
        return this;
    }
}
