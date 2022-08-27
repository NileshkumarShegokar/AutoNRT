package com.ns.AutoNRT.ui.env;

import com.ns.AutoNRT.entity.Environment;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

@Route(value = "")
@Theme(themeFolder = "flowcrmtutorial")
@PageTitle("Environments | Vaadin CRM")
public class EnvironmentView extends VerticalLayout {
    Grid<Environment> grid = new Grid<>(Environment.class);
    TextField filterText = new TextField();

    public EnvironmentView() {
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("Environment-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addEnvironmentButton = new Button("Add Environment");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addEnvironmentButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}