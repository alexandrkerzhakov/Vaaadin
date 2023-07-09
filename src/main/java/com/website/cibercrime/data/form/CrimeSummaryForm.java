package com.website.cibercrime.data.form;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
import com.website.cibercrime.data.entity.CrimeSummary;
import com.website.cibercrime.data.entity.Phone;

import java.util.Arrays;

public class CrimeSummaryForm extends FormLayout {
    Binder<CrimeSummary> crimeSummaryBinder = new Binder<>(CrimeSummary.class);

    Grid<CrimeSummary> grid = new Grid<>(CrimeSummary.class);
    ComboBox<String> areaComboBox = new ComboBox<>("Территориальный орган");

    {
        areaComboBox.setItems("XXX", "DDD");
    }

    TextField messageNumber = new TextField("КУСП");
    DatePicker messageDate = new DatePicker("Дата регистрации КУСП");
    TextField caseNumber = new TextField("УД");
    DatePicker caseNumberDate = new DatePicker("Дата возбуждения УД");
    TextField message = new TextField("Текст сообщения");
    TextField claimantFirstName = new TextField("Имя заявителя");
    TextField claimantSecondName = new TextField("Фамилия заявителя");
    TextField claimantFatherName = new TextField("Отчество заявителя");

    TextField claimantPhones = new TextField("Телефоны заявителя");
//    List<TextField> claimantsPhone = new ArrayList<>();
//    Grid<Phone> claimantsPhone = new Grid<Phone>("Список телефонов заявителя");

    //    TextField scammer = new TextField("Преступник");
    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    public void setCrimeSummary(CrimeSummary crimeSummary) {
        crimeSummaryBinder.setBean(crimeSummary);
    }

    public CrimeSummaryForm() {
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeReport().getMessageNumber()).setHeader("КУСП");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeReport().getMessageDate()).setHeader("Дата регистрации КУСП");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeCase().getCaseNumber()).setHeader("УД");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeCase().getCaseNumberDate()).setHeader("Дата возбуждения УД");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeReport().getMessage()).setHeader("Текст сообщения");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getFirstName()).setHeader("Имя заявителя");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getFatherName()).setHeader("Фамилия заявителя");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getSecondName()).setHeader("Отчество заявителя");

        addClassName("message-form");

        crimeSummaryBinder.forField(areaComboBox)
                .bind("areaComboBox");

        crimeSummaryBinder.forField(messageNumber)
                .withConverter(new StringToIntegerConverter("messageNumber"))
                .bind(crimeSummary -> crimeSummary.getCrimeReport().getMessageNumber(),
                        ((crimeSummary, messageNumber) -> crimeSummary.getCrimeReport().setMessageNumber(messageNumber)));

        crimeSummaryBinder.forField(messageDate)
                .bind(crimeSummary -> crimeSummary.getCrimeReport().getMessageDate(),
                        ((crimeSummary, messageDate) -> crimeSummary.getCrimeReport().setMessageDate(messageDate)));

        crimeSummaryBinder.forField(caseNumber)
                .withConverter(new StringToIntegerConverter("caseNumber"))
                .bind(crimeSummary -> crimeSummary.getCrimeCase().getCaseNumber(),
                        ((crimeSummary, caseNumber) -> crimeSummary.getCrimeCase().setCaseNumber(caseNumber)));

        crimeSummaryBinder.forField(caseNumberDate)
                .bind(crimeSummary -> crimeSummary.getCrimeCase().getCaseNumberDate(),
                        ((crimeSummary, caseNumberDate) -> crimeSummary.getCrimeCase().setCaseNumberDate(caseNumberDate)));

        crimeSummaryBinder.forField(message)
                .bind(crimeSummary -> crimeSummary.getCrimeReport().getMessage(),
                        ((crimeSummary, message) -> crimeSummary.getCrimeReport().setMessage(message)));

        crimeSummaryBinder.forField(claimantFirstName)
                .bind(crimeSummary -> crimeSummary.getClaimant().getFirstName(),
                        (crimeSummary, firstName) -> crimeSummary.getClaimant().setFirstName(firstName));

        crimeSummaryBinder.forField(claimantSecondName)
                .bind(crimeSummary -> crimeSummary.getClaimant().getSecondName(),
                        (crimeSummary, secondName) -> crimeSummary.getClaimant().setSecondName(secondName));

        crimeSummaryBinder.forField(claimantFatherName)
                .bind(crimeSummary -> crimeSummary.getClaimant().getFatherName(),
                        (crimeSummary, fatherName) -> crimeSummary.getClaimant().setFatherName(fatherName));

        crimeSummaryBinder.forField(claimantPhones)
                .bind(
                        crimeSummary -> String.join(",", crimeSummary.getClaimant().getPhones().stream().map(Phone::getNumber).toList()),
                        (crimeSummary, phoneLine) -> crimeSummary.getClaimant().setPhones(Arrays
                                .stream(phoneLine.split(","))
                                .map(phoneString -> new Phone(phoneString))
                                .toList())
                );

        add(
                areaComboBox,
                messageNumber,
                messageDate,
                caseNumber,
                caseNumberDate,
                message,
                claimantFirstName,
                claimantSecondName,
                claimantFatherName,
                claimantPhones,
                createButtonsLayout()
        );
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, crimeSummaryBinder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        crimeSummaryBinder.addStatusChangeListener(e -> save.setEnabled(crimeSummaryBinder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (crimeSummaryBinder.isValid()) {
            fireEvent(new SaveEvent(this, crimeSummaryBinder.getBean()));
        }
    }

    public static abstract class CrimeSummaryFormEvent extends ComponentEvent<CrimeSummaryForm> {
        private CrimeSummary crimeSummary;

        protected CrimeSummaryFormEvent(CrimeSummaryForm messageForm, CrimeSummary crimeSummary) {
            super(messageForm, false);
            this.crimeSummary = crimeSummary;
        }


        public CrimeSummary getCrimeSummary() {
            return crimeSummary;
        }
    }

    public static class SaveEvent extends CrimeSummaryFormEvent {
        SaveEvent(CrimeSummaryForm messageForm, CrimeSummary crimeSummary) {
            super(messageForm, crimeSummary);
        }
    }

    public static class DeleteEvent extends CrimeSummaryFormEvent {
        DeleteEvent(CrimeSummaryForm messageForm, com.website.cibercrime.data.entity.CrimeSummary crimeSummary) {
            super(messageForm, crimeSummary);
        }

    }

    public static class CloseEvent extends CrimeSummaryFormEvent {
        CloseEvent(CrimeSummaryForm messageForm) {
            super(messageForm, null);
        }

    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
