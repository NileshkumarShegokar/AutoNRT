package com.ns.autoNRT.views.testcase;


import com.ns.autoNRT.data.entity.TestCase;
import com.ns.autoNRT.data.service.TestCaseService;
import com.ns.autoNRT.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;


@Component
@Scope("prototype")
@Route(value="testcase", layout = MainLayout.class)
@PageTitle("TestCase | AutoNRT")
@PermitAll
public class TestCaseView extends VerticalLayout {
    Grid<TestCase> grid = new Grid<>(TestCase.class);
    TextField filterText = new TextField();
    TestCaseForm form;
    TestCaseService service;

    public TestCaseView(TestCaseService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new TestCaseForm();
        form.setWidth("25em");
        form.addListener(TestCaseForm.SaveEvent.class, this::saveTestCase);
        form.addListener(TestCaseForm.DeleteEvent.class, this::deleteTestCase);
        form.addListener(TestCaseForm.CloseEvent.class, e -> closeEditor());

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
            editTestCase(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name","uri", "type", "body","contentType");
       grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addTestCaseButton = new Button("Add Test Case");
        addTestCaseButton.addClickListener(click -> addTestCase());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addTestCaseButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveTestCase(TestCaseForm.SaveEvent event) {
        service.saveTestCase(event.getTestCase());
        updateList();
        closeEditor();
    }

    private void deleteTestCase(TestCaseForm.DeleteEvent event) {
        service.deleteTestCase(event.getTestCase());
        updateList();
        closeEditor();
    }

    public void editTestCase(TestCase testCase) {
        if (testCase == null) {
            closeEditor();
        } else {
            form.setTestCase(testCase);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addTestCase() {
        grid.asSingleSelect().clear();
        editTestCase(new TestCase());
    }

    private void closeEditor() {
        form.setTestCase(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllTestCases(filterText.getValue()));
    }


}
