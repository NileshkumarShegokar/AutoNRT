package com.ns.autoNRT.views.testcase;

import com.ns.autoNRT.data.entity.TestCase;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCaseForm extends FormLayout {
  private TestCase testCase;

  TextField name = new TextField("Name");
  TextField uri = new TextField("API URI");
  ComboBox<String> type = new ComboBox<>("Method");
  ComboBox<String> contentType = new ComboBox<>("Content Type");
  TextArea body=new TextArea("Body");
  Binder<TestCase> binder = new BeanValidationBinder<>(TestCase.class);

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public TestCaseForm() {
    addClassName("contact-form");
    binder.bindInstanceFields(this);
    type.setItems(Arrays.asList("GET","POST","PUT","DELETE"));
    contentType.setItems(Arrays.asList("Application/JSON","XML","PLAIN TEXT"));
    add(name,
        uri,type,contentType,body,
        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, testCase)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }

  public void setTestCase(TestCase testCase) {
    this.testCase = testCase;
    binder.readBean(testCase);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(testCase);
      fireEvent(new SaveEvent(this, testCase));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class TestCaseFormEvent extends ComponentEvent<TestCaseForm> {
    private TestCase testCase;

    protected TestCaseFormEvent(TestCaseForm source, TestCase testCase) {
      super(source, false);
      this.testCase = testCase;
    }

    public TestCase getTestCase() {
      return testCase;
    }
  }

  public static class SaveEvent extends TestCaseFormEvent {
    SaveEvent(TestCaseForm source, TestCase testCase) {
      super(source, testCase);
    }
  }

  public static class DeleteEvent extends TestCaseFormEvent {
    DeleteEvent(TestCaseForm source, TestCase testCase) {
      super(source, testCase);
    }

  }

  public static class CloseEvent extends TestCaseFormEvent {
    CloseEvent(TestCaseForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }
}