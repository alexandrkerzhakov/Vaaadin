package com.website.cibercrime.data.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.website.cibercrime.data.entity.Area;

public class Message extends FormLayout {

    ComboBox<Area> areaComboBox = new ComboBox<>("Район");

    TextField messageNumber = new TextField("КУСП");

    DatePicker dateMessage = new DatePicker("Дата КУСП");

    TextField caseNumber = new TextField("УД");

    DatePicker dateCaseNumber = new DatePicker("Дата УД");

    TextField message = new TextField("Текст сообщения");

    TextField claimant = new TextField("Заявитель");

    TextField scammer = new TextField("Преступник");
    Button save = new Button("Сохранить");
    Button update = new Button("Редактировать");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

//    public Message(List<Company> companies, List<Status> statuses) {
//        addClassName("contact-form");
//
//        company.setItems(companies);
//        company.setItemLabelGenerator(Company::getName);
//        status.setItems(statuses);
//        status.setItemLabelGenerator(Status::getName);
//
//        add(firstName,
//                lastName,
//                email,
//                company,
//                status,
//                createButtonsLayout());
//    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, update, delete, close);
    }
}
