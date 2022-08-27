package com.ns.autoNRT.views.env;

import com.ns.autoNRT.data.entity.Environment;
import com.ns.autoNRT.data.service.EnvironmentService;
import com.ns.autoNRT.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;


@Component
@Scope("prototype")
@Route(value="", layout = MainLayout.class)
@PageTitle("Environment | AutoNRT")
@PermitAll
public class EnvironmentView extends VerticalLayout {
    Grid<Environment> grid = new Grid<>(Environment.class);
    TextField filterText = new TextField();
    EnvironmentForm form;
    EnvironmentService service;

    public EnvironmentView(EnvironmentService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new EnvironmentForm();
        form.setWidth("25em");
        form.addListener(EnvironmentForm.SaveEvent.class, this::saveEnvironment);
        form.addListener(EnvironmentForm.DeleteEvent.class, this::deleteEnvironment);
        form.addListener(EnvironmentForm.CloseEvent.class, e -> closeEditor());

        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event ->
            editEnvironment(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "domain", "authentication","username","password","token");
       grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addEnvironmentButton = new Button("Add Environment");
        addEnvironmentButton.addClickListener(click -> addEnvironment());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addEnvironmentButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveEnvironment(EnvironmentForm.SaveEvent event) {
        service.saveEnvironment(event.getEnvironment());
        updateList();
        closeEditor();
    }

    private void deleteEnvironment(EnvironmentForm.DeleteEvent event) {
        service.deleteEnvironment(event.getEnvironment());
        updateList();
        closeEditor();
    }

    public void editEnvironment(Environment environment) {
        if (environment == null) {
            closeEditor();
        } else {
            form.setEnvironment(environment);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addEnvironment() {
        grid.asSingleSelect().clear();
        editEnvironment(new Environment());
    }

    private void closeEditor() {
        form.setEnvironment(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllEnvironments(filterText.getValue()));
    }


}
