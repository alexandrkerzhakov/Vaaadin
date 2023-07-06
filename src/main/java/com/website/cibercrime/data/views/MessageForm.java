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
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
import com.website.cibercrime.data.entity.Claimant;
import com.website.cibercrime.data.entity.CrimeReport;
import org.hibernate.event.spi.DeleteEvent;
import org.jsoup.select.Collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MessageForm extends FormLayout {
    Binder<CrimeReport> crimeReportBinder = new Binder<>(CrimeReport.class);
    ComboBox<String> areaComboBox = new ComboBox<>("Район");

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

    public void setCrimeReport(CrimeReport crimeReport) {
        crimeReportBinder.setBean(crimeReport);
    }
//    public void setCrimeReport(CrimeReport crimeReport) {
//        crimeReportBinder.setBean(crimeReport);
//    }

    public MessageForm() {
//        Claimant claimant = new Claimant();
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

//        crimeReportBinder.forField(claimant)
//                .withConverter(new MyConverter())
//                .bind("claimant");

        crimeReportBinder.forField(claimantFirstName)
                .bind(crimeReport -> crimeReport.getClaimant().getFirstName(),
                        (crimeReport, firstName) -> crimeReport.getClaimant().setFirstName(firstName));

        crimeReportBinder.forField(claimantSecondName)
                .bind(crimeReport -> crimeReport.getClaimant().getSecondName(),
                        (crimeReport, secondName) -> crimeReport.getClaimant().setSecondName(secondName));

        crimeReportBinder.forField(claimantFatherName)
                .bind(crimeReport -> crimeReport.getClaimant().getFatherName(),
                        (crimeReport, fatherName) -> crimeReport.getClaimant().setFatherName(fatherName));

        crimeReportBinder.forField(claimantPhones)
                .bind(crimeReport -> String.join(",", crimeReport.getClaimant().getPhones()),
                        (crimeReport, phoneList) -> crimeReport.getClaimant().setPhones(Arrays.stream(phoneList.split(",")).toList()));

//        crimeReportBinder.bindInstanceFields(this);
//        claimantBinder.bind(claimant, Claimant::getFirstName, Claimant::setFirstName);
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
