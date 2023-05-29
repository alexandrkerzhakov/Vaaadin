package com.website.cibercrime.data.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.website.cibercrime.data.entity.CrimeReport;

@Route("inputData")
public class InputData extends VerticalLayout {

    Grid<CrimeReport> grid = new Grid<>(CrimeReport.class, false);
    TextField filterText = new TextField();

    public InputData() {
        addClassName("inputData");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("crimeReport-grid");
        grid.setSizeFull();
        grid.addColumn("area").setHeader("Территориальный орган");
        grid.addColumn("messageNumber").setHeader("КУСП");
        grid.addColumn("messageDate").setHeader("Дата КУСП");
        grid.addColumn("caseNumber").setHeader("УД");
        grid.addColumn("caseNumberDate").setHeader("Дата УД");
        grid.addColumn("message").setHeader("Текст сообщения");
        grid.addColumn("claimant").setHeader("Заявитель");
        grid.addColumn("scammer").setHeader("Преступник");
//        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
//        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        Button addCrimeReportButton = new Button("Добавить запись");
        var toolbar = new HorizontalLayout(filterText, addCrimeReportButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

}
