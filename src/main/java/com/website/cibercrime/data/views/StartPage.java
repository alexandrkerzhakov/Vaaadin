package com.website.cibercrime.data.views;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.ArrayList;
import java.util.List;

@PageTitle("startPage")
@Route("")
public class StartPage extends VerticalLayout {
//    List<String> list = new ArrayList<>();
//    ComboBox<String> comboBox = new ComboBox("sss");
//    {
//        list.add("dddd");
//        list.add("ssss");
//        list.add("ddwww");
//    }


//    Test2 test2 = new Test2("testov", "rrr");

    public StartPage() {
        setSpacing(false);

        H1 header = new H1("База данных для учета преступлений, совершаемых в IT-сфере");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);

        Image img = new Image("images/K.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        Anchor anchor = new Anchor("inputData", "Форма ввода");
        add(anchor);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
