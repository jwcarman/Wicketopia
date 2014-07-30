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

package org.wicketopia.persistence.component.scaffold;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.wicketopia.Wicketopia;
import org.wicketopia.context.Context;
import org.wicketopia.factory.PropertyComponentFactory;
import org.wicketopia.layout.view.CssBeanViewLayoutPanel;
import org.wicketopia.metadata.WicketopiaBeanFacet;
import org.wicketopia.model.column.FragmentColumn;
import org.wicketopia.model.label.DisplayNameModel;
import org.wicketopia.model.label.PluralizedModel;
import org.wicketopia.persistence.PersistencePlugin;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.component.link.ajax.AjaxCreateLink;
import org.wicketopia.persistence.component.link.ajax.AjaxUpdateLink;
import org.wicketopia.persistence.model.LoadableDetachableEntityModel;
import org.wicketopia.persistence.model.repeater.PersistenceDataProvider;

import java.util.List;

/**
 * @author James Carman
 */
public class Scaffold<T> extends Panel implements IHeaderContributor {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    public static final int DEFAULT_ROWS_PER_PAGE = 25;
    private static final PackageResourceReference CSS_REFERENCE = new PackageResourceReference(Scaffold.class, "scaffold.css");
    private static final String CONTENT_ID = "content";
    private final Class<T> beanType;
    private final PersistenceProvider persistenceProvider;
    private ScaffoldMode mode = ScaffoldMode.List;
    private final FeedbackPanel feedback = new FeedbackPanel("feedback");
    private IModel<T> model;
    private final DisplayNameModel displayName;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public Scaffold(String id, Class<T> beanType) {
        this(id, beanType, PersistencePlugin.get().getPersistenceProvider());
    }

    public Scaffold(String id, Class<T> beanType, PersistenceProvider persistenceProvider) {
        super(id);
        WicketopiaBeanFacet beanFacet = WicketopiaBeanFacet.get(Wicketopia.get().getBeanMetaData(beanType));
        displayName = new DisplayNameModel(beanFacet);
        add(new AttributeModifier("class", new Model<String>("scaffold")));
        feedback.setOutputMarkupPlaceholderTag(true);
        feedback.setFilter(new ContainerFeedbackMessageFilter(this));
        add(feedback);
        this.beanType = beanType;
        this.persistenceProvider = persistenceProvider;
        refreshContent(null);
        setOutputMarkupPlaceholderTag(true);
    }

    private void refreshContent(AjaxRequestTarget target) {
        final Component content = createContent();
        content.setOutputMarkupPlaceholderTag(true);
        addOrReplace(content);
        if (target != null) {
            target.add(this);
        }
    }

    private Component createContent() {
        switch (mode) {
            case List:
                return new ListFragment();
            case View:
                return new ViewFragment();
            case Update:
                return new EditFragment();
            case Create:
                return new CreateFragment();
        }
        return new EmptyPanel(CONTENT_ID);
    }

//----------------------------------------------------------------------------------------------------------------------
// IHeaderContributor Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(CSS_REFERENCE));
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    private Context createContext(String mode) {
        return new Context(mode);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        displayName.detach();
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private final class ActionsColumn extends FragmentColumn<T> {
        private ActionsColumn() {
            super(new Model<String>("Actions"));
        }

        @Override
        protected Fragment createFragment(String componentId, IModel<T> rowModel) {
            Fragment f = new Fragment(componentId, "actions", Scaffold.this);
            f.add(new ViewLink("viewLink", rowModel));
            f.add(new ScaffoldEditLink("updateLink", rowModel));
            f.add(new ScaffoldDeleteLink("deleteLink", rowModel));
            return f;
        }
    }

    private final class ConfirmBehavior extends Behavior {
        private final String event;
        private final IModel<String> message;

        private ConfirmBehavior(String event, IModel<String> message) {
            this.event = event;
            this.message = message;
        }

        @Override
        public void detach(Component component) {
            super.detach(component);
            message.detach();
        }

        public void onComponentTag(Component component, ComponentTag tag) {
            StringBuilder handler = new StringBuilder();
            handler.append("if (!confirm('");
            handler.append(message.getObject().replaceAll("'", "\\\\'"));
            handler.append("')) {return false;} ");

            String script = tag.getAttributes().getString(event);
            if (script != null) {
                handler.append(script);
            }

            tag.put(event, handler.toString());
        }
    }

    private final class CreateFragment extends Fragment {
        private CreateFragment() {
            super(CONTENT_ID, "create", Scaffold.this);
            model = new CreateModel();
            final ScaffoldListLink listLink = new ScaffoldListLink("listButton");
            add(listLink.add(new Label("nameList", displayName).setRenderBodyOnly(true)));
            add(new Label("nameCaption", displayName).setRenderBodyOnly(true));
            final Form<T> form = new Form<T>("form", model);
            final PropertyComponentFactory<T> editorFactory = Wicketopia.get().createEditorFactory(beanType);
            final Context context = createContext(Context.CREATE);
            form.add(new CssBeanViewLayoutPanel<T>("layout", beanType, model, context, editorFactory));
            add(new SaveLink(form));
            add(form);
        }
    }

    private final class CreateModel extends LoadableDetachableModel<T> {
        @Override
        protected T load() {
            try {
                return beanType.newInstance();
            } catch (Exception e) {
                throw new WicketRuntimeException("Unable to instantiate " + beanType.getName() + " object (" + e.getMessage() + ").", e);
            }
        }
    }

    private final class EditFragment extends Fragment {
        private EditFragment() {
            super(CONTENT_ID, "edit", Scaffold.this);
            add(new Label("nameCaption", displayName).setRenderBodyOnly(true));
            add(new ScaffoldDeleteLink("deleteButton", model));
            add(new ScaffoldListLink("listButton").add(new Label("nameList", displayName).setRenderBodyOnly(true)));
            add(new ScaffoldCreateLink("createButton").add(new Label("nameCreate", displayName).setRenderBodyOnly(true)));
            final Form<T> form = new Form<T>("form", model);
            final PropertyComponentFactory<T> editorFactory = Wicketopia.get().createEditorFactory(beanType);
            final Context context = createContext(Context.UPDATE);
            form.add(new CssBeanViewLayoutPanel<T>("layout", beanType, model, context, editorFactory));
            add(form);
            add(new ScaffoldUpdateLink<T>(form));
        }
    }

    private final class ListFragment extends Fragment {
        private ListFragment() {
            super(CONTENT_ID, "list", Scaffold.this);
            final ScaffoldCreateLink create = new ScaffoldCreateLink("newEntity");
            add(create);
            add(new Label("pluralName", new PluralizedModel(displayName)).setRenderBodyOnly(true));
            create.add(new Label("displayName", displayName).setRenderBodyOnly(true));
            final PropertyComponentFactory<T> viewerFactory = Wicketopia.get().createViewerFactory(beanType);
            final Context context = createContext(Context.LIST);
            final List<IColumn<T, String>> columns = Wicketopia.get().createColumns(beanType, viewerFactory, context);
            columns.add(new ActionsColumn());
            add(new AjaxFallbackDefaultDataTable<T, String>("table", columns, new PersistenceDataProvider<T>(beanType, persistenceProvider), DEFAULT_ROWS_PER_PAGE));
        }
    }

    private class SaveLink extends AjaxCreateLink<T> {
        public SaveLink(Form<T> form) {
            super("saveButton", form, persistenceProvider);
        }

        @Override
        protected void afterCreate(T object, AjaxRequestTarget target) {
            Scaffold.this.mode = ScaffoldMode.View;
            Scaffold.this.info(displayName.getObject() + " Created");
            model = new LoadableDetachableEntityModel<T>(beanType, object, persistenceProvider);
            refreshContent(target);
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(feedback);
        }
    }

    private final class ScaffoldCreateLink extends AjaxLink<Void> {
        private ScaffoldCreateLink(String id) {
            super(id);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            mode = ScaffoldMode.Create;
            refreshContent(target);
        }
    }

    private final class ScaffoldDeleteLink extends AjaxLink<T> {
        private ScaffoldDeleteLink(String id, IModel<T> tiModel) {
            super(id, tiModel);
            add(new ConfirmBehavior("onclick", new Model<String>("Are you sure?")));
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            persistenceProvider.delete(getModelObject());
            Scaffold.this.info(displayName.getObject() + " Deleted");
            mode = ScaffoldMode.List;
            refreshContent(target);
        }
    }

    private final class ScaffoldEditLink extends AjaxLink<T> {
        private ScaffoldEditLink(String id, IModel<T> model) {
            super(id, model);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            model = getModel();
            mode = ScaffoldMode.Update;
            refreshContent(target);
        }
    }

    private final class ScaffoldListLink extends AjaxLink<Void> {
        private ScaffoldListLink(String id) {
            super(id);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            mode = ScaffoldMode.List;
            refreshContent(target);
        }
    }

    private class ScaffoldUpdateLink<T> extends AjaxUpdateLink<T> {
        public ScaffoldUpdateLink(Form<T> form) {
            super("saveButton", form, persistenceProvider);
        }

        @Override
        protected void afterUpdate(T object, AjaxRequestTarget target) {
            mode = ScaffoldMode.View;
            Scaffold.this.info(displayName.getObject() + " Updated");
            refreshContent(target);
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(feedback);
        }
    }

    private final class ViewFragment extends Fragment {
        private ViewFragment() {
            super(CONTENT_ID, "view", Scaffold.this);
            add(new Label("nameCaption", displayName).setRenderBodyOnly(true));
            add(new ScaffoldEditLink("editButton", model));
            add(new ScaffoldDeleteLink("deleteButton", model));
            add(new ScaffoldListLink("listButton").add(new Label("nameList", displayName).setRenderBodyOnly(true)));
            add(new ScaffoldCreateLink("createButton").add(new Label("nameCreate", displayName).setRenderBodyOnly(true)));
            final PropertyComponentFactory<T> factory = Wicketopia.get().createViewerFactory(beanType);
            final Context context = createContext(Context.VIEW);
            add(new CssBeanViewLayoutPanel<T>("layout", beanType, model, context, factory));
        }
    }

    private final class ViewLink extends AjaxLink<T> {
        private ViewLink(String id, IModel<T> tiModel) {
            super(id, tiModel);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            model = getModel();
            mode = ScaffoldMode.View;
            refreshContent(target);
        }
    }
}
