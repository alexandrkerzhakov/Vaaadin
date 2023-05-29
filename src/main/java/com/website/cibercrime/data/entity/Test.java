package com.website.cibercrime.data.entity;

import com.vaadin.flow.component.combobox.ComboBox;

import java.util.ArrayList;
import java.util.List;

public enum Test {
    Ret;

    public static void main(String[] args) {
        Test[] array = Test.values();
        System.out.println(array[0]);
        List<String> list = new ArrayList<>();
        list.add("dddd");
        list.add("ssss");
        list.add("ddwww");
        ComboBox<String> comboBox = new ComboBox("sss");
        comboBox.setItems(list);

    }
}
