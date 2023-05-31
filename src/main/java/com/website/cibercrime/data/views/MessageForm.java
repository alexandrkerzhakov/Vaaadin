package com.website.cibercrime.data.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
//import com.website.cibercrime.data.entity.Area;
//import com.website.cibercrime.data.entity.Claimant;
import com.website.cibercrime.data.entity.CrimeReport;
import org.hibernate.event.spi.DeleteEvent;

import java.util.ArrayList;
import java.util.List;

public class MessageForm extends FormLayout {
    Binder<CrimeReport> crimeReportBinder = new Binder<>(CrimeReport.class);

    //    Binder<Claimant> claimantBinder = new Binder<>(Claimant.class);
    ComboBox<String> areaComboBox = new ComboBox<>("Район");
    {
        areaComboBox.setItems("XXX", "DDD");
    }
    TextField messageNumber = new TextField("КУСП");
    DatePicker messageDate = new DatePicker("Дата регистрации КУСП");
    TextField caseNumber = new TextField("УД");
    DatePicker caseNumberDate = new DatePicker("Дата возбуждения УД");
    TextField message = new TextField("Текст сообщения");

//    TextField claimant = new TextField("Заявитель");

    //    TextField scammer = new TextField("Преступник");
    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    public void setCrimeReport(CrimeReport crimeReport) {
        crimeReportBinder.setBean(crimeReport);
    }

    public MessageForm() {
        addClassName("message-form");

        crimeReportBinder.forField(areaComboBox)
//                .bind(CrimeReport::getAreaComboBox, CrimeReport::setAreaComboBox);
                .bind("areaComboBox");

        crimeReportBinder.forField(messageNumber)
                .withConverter(new StringToIntegerConverter("Number"))
                .bind("messageNumber");

        crimeReportBinder.forField(messageDate)
//                .withConverter(new StringToDateConverter())
                .bind("messageDate");

        crimeReportBinder.forField(caseNumber)
                .withConverter(new StringToIntegerConverter("Number"))
                .bind("caseNumber");


        crimeReportBinder.forField(caseNumberDate)
//                .withConverter(new StringToDateConverter())
                .bind("caseNumberDate");

        crimeReportBinder.forField(message)
                .bind("message");
//        crimeReportBinder.bindInstanceFields(this);
//        claimantBinder.bind(claimant, Claimant::getFirstName, Claimant::setFirstName);
        add(
                areaComboBox,
                messageNumber,
                messageDate,
                caseNumber,
                caseNumberDate,
                message,
//                claimant,
//                scammer,
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, crimeReportBinder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        crimeReportBinder.addStatusChangeListener(e -> save.setEnabled(crimeReportBinder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (crimeReportBinder.isValid()) {
            fireEvent(new SaveEvent(this, crimeReportBinder.getBean()));
        }
    }

    public static abstract class CrimeReportFormEvent extends ComponentEvent<MessageForm> {
        private CrimeReport crimeReport;

        protected CrimeReportFormEvent(MessageForm messageForm, CrimeReport crimeReport) {
            super(messageForm, false);
            this.crimeReport = crimeReport;
        }


        public CrimeReport getCrimeReport() {
            return crimeReport;
        }
    }

    public static class SaveEvent extends CrimeReportFormEvent {
        SaveEvent(MessageForm messageForm, CrimeReport crimeReport) {
            super(messageForm, crimeReport);
        }
    }

    public static class DeleteEvent extends CrimeReportFormEvent {
        DeleteEvent(MessageForm messageForm, CrimeReport crimeReport) {
            super(messageForm, crimeReport);
        }

    }

    public static class CloseEvent extends CrimeReportFormEvent {
        CloseEvent(MessageForm messageForm) {
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
