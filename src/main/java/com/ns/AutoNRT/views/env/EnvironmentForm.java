package com.ns.autoNRT.views.env;

import com.ns.autoNRT.data.entity.Environment;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class EnvironmentForm extends FormLayout {
  private Environment environment;

  TextField name = new TextField("Name");
  TextField domain = new TextField("Domain URL");
  Checkbox authentication=new Checkbox("Authentication");
  TextField username = new TextField("username");
  PasswordField password = new PasswordField("Password");
  PasswordField token = new PasswordField("Token");
  Binder<Environment> binder = new BeanValidationBinder<>(Environment.class);

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public EnvironmentForm() {
    addClassName("contact-form");
    binder.bindInstanceFields(this);

    add(name,
        domain,authentication, username,password,token,
        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, environment)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
    binder.readBean(environment);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(environment);
      fireEvent(new SaveEvent(this, environment));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class EnvironmentFormEvent extends ComponentEvent<EnvironmentForm> {
    private Environment environment;

    protected EnvironmentFormEvent(EnvironmentForm source, Environment environment) {
      super(source, false);
      this.environment = environment;
    }

    public Environment getEnvironment() {
      return environment;
    }
  }

  public static class SaveEvent extends EnvironmentFormEvent {
    SaveEvent(EnvironmentForm source, Environment environment) {
      super(source, environment);
    }
  }

  public static class DeleteEvent extends EnvironmentFormEvent {
    DeleteEvent(EnvironmentForm source, Environment environment) {
      super(source, environment);
    }

  }

  public static class CloseEvent extends EnvironmentFormEvent {
    CloseEvent(EnvironmentForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }
}