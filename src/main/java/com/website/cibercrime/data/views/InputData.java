package com.website.cibercrime.data.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.website.cibercrime.data.entity.CrimeReport;
import com.website.cibercrime.data.service.CrmService;

@Route("inputData")
public class InputData extends VerticalLayout {

    Grid<CrimeReport> grid = new Grid<>(CrimeReport.class, false);
    TextField filterText = new TextField();
    MessageForm messageForm;
    CrmService crmService;

    public InputData(CrmService crmService) {
        this.crmService = crmService;
        addClassName("inputData");
        setSizeFull();
        configureGrid();
        configureMessageForm();
        add(getToolbar(), getMessage());
        updateReport();
        closeEditor();
    }

    private void updateReport() {
        grid.setItems(crmService.findAllCrimeReports(filterText.getValue()));
    }

    private Component getMessage() {
        HorizontalLayout content = new HorizontalLayout(grid, messageForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, messageForm);
        content.addClassNames("message");
        content.setSizeFull();
        return content;
    }

    private void configureMessageForm() {
        messageForm = new MessageForm();
        messageForm.setWidth("25em");
        messageForm.addSaveListener(this::saveCrimeReport);
        messageForm.addDeleteListener(this::deleteCrimeReport);
        messageForm.addCloseListener(e -> closeEditor());

    }

    private void saveCrimeReport(MessageForm.SaveEvent event) {
        System.out.println("SAVE");
        crmService.saveCrimeReport(event.getCrimeReport());
        updateReport();
        closeEditor();
    }

    private void deleteCrimeReport(MessageForm.DeleteEvent event) {
        System.out.println("DELETE");
        crmService.deleteCrimeReport(event.getCrimeReport());
        updateReport();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("crimeReport-grid");
        grid.setSizeFull();
        grid.addColumn("areaComboBox").setHeader("Территориальный орган");
        grid.addColumn("messageNumber").setHeader("КУСП");
        grid.addColumn("messageDate").setHeader("Дата регистрации КУСП");
        grid.addColumn("caseNumber").setHeader("УД");
        grid.addColumn("caseNumberDate").setHeader("Дата возбуждения УД");
        grid.addColumn("message").setHeader("Текст сообщения");
        grid.addColumn(crimeReport -> crimeReport.getClaimant().getFirstName()).setHeader("Имя заявителя");
        grid.addColumn(crimeReport -> crimeReport.getClaimant().getFatherName()).setHeader("Фамилия заявителя");
        grid.addColumn(crimeReport -> crimeReport.getClaimant().getFatherName()).setHeader("Отчество заявителя");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editCrimeReport(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateReport());
        Button addCrimeReportButton = new Button("Добавить запись");
        addCrimeReportButton.addClickListener(click -> addCrimeReport());
        var toolbar = new HorizontalLayout(filterText, addCrimeReportButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editCrimeReport(CrimeReport crimeReport) {
        if (crimeReport == null) {
            closeEditor();
        } else {
            messageForm.setCrimeReport(crimeReport);
            messageForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        messageForm.setCrimeReport(null);
        messageForm.setVisible(false);
        removeClassName("editing");
    }

    private void addCrimeReport() {
        grid.asSingleSelect().clear();
        editCrimeReport(new CrimeReport());
    }

}
