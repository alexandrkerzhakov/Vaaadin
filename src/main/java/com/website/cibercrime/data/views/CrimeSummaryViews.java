package com.website.cibercrime.data.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.website.cibercrime.data.entity.CrimeSummary;
import com.website.cibercrime.data.entity.Phone;
import com.website.cibercrime.data.form.CrimeSummaryForm;
import com.website.cibercrime.data.service.CrmService;

@Route("crimeSummaryViews")
public class CrimeSummaryViews extends VerticalLayout {

    Grid<CrimeSummary> grid = new Grid<>(CrimeSummary.class, false);
    TextField filterText = new TextField();
    CrimeSummaryForm crimeSummaryForm;
    CrmService crmService;

    public CrimeSummaryViews(CrmService crmService) {
        this.crmService = crmService;
        addClassName("crimeSummaryViews");
        setSizeFull();
        configureGrid();
        configureCrimeSummaryForm();
        add(getToolbar(), getMessage());
        updateCrimeSummary();
        closeEditor();
    }

    private void updateCrimeSummary() {
        grid.setItems(crmService.findAllCrimeSummarys(filterText.getValue()));
    }

    private Component getMessage() {
        HorizontalLayout content = new HorizontalLayout(grid, crimeSummaryForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, crimeSummaryForm);
        content.addClassNames("message");
        content.setSizeFull();
        return content;
    }

    private void configureCrimeSummaryForm() {
        crimeSummaryForm = new CrimeSummaryForm();
        crimeSummaryForm.setWidth("25em");
        crimeSummaryForm.addSaveListener(this::saveCrimeSummaryEvent);
        crimeSummaryForm.addDeleteListener(this::deleteCrimeSummaryEvent);
        crimeSummaryForm.addCloseListener(e -> closeEditor());

    }

    private void saveCrimeSummaryEvent(CrimeSummaryForm.SaveEvent event) {
        System.out.println("SAVE");
        crmService.saveCrimeSummary(event.getCrimeSummary());
        updateCrimeSummary();
        closeEditor();
    }

    private void deleteCrimeSummaryEvent(CrimeSummaryForm.DeleteEvent event) {
        System.out.println("DELETE");
        crmService.deleteCrimeSummary(event.getCrimeSummary());
        updateCrimeSummary();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("crimeSummaryViews-grid");
        grid.setSizeFull();
        grid.addColumn("areaComboBox").setHeader("Территориальный орган");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeReport().getMessageNumber()).setHeader("КУСП");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeReport().getMessageDate()).setHeader("Дата регистрации КУСП");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeCase().getCaseNumber()).setHeader("УД");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeCase().getCaseNumberDate()).setHeader("Дата возбуждения УД");
        grid.addColumn(crimeSummary -> crimeSummary.getCrimeReport().getMessage()).setHeader("Текст сообщения");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getFirstName()).setHeader("Имя заявителя");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getFatherName()).setHeader("Фамилия заявителя");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getSecondName()).setHeader("Отчество заявителя");
        grid.addColumn(crimeSummary -> crimeSummary.getClaimant().getPhones().stream().map(Phone::getNumber).toList()).setHeader("Список телефонов заявителя");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editCrimeSummaryForm(event.getValue()));
    }


    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateCrimeSummary());
        Button addCrimeReportButton = new Button("Добавить запись");
        addCrimeReportButton.addClickListener(click -> addCrimeSummary());
        var toolbar = new HorizontalLayout(filterText, addCrimeReportButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editCrimeSummaryForm(CrimeSummary crimeSummary) {
        if (crimeSummary == null) {
            closeEditor();
        } else {
            crimeSummaryForm.setCrimeSummary(crimeSummary);
            crimeSummaryForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        crimeSummaryForm.setCrimeSummary(null);
        crimeSummaryForm.setVisible(false);
        removeClassName("editing");
    }

    private void addCrimeSummary() {
        grid.asSingleSelect().clear();
        editCrimeSummaryForm(new CrimeSummary());
    }

}
