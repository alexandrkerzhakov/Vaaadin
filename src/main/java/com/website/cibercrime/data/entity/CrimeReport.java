package com.website.cibercrime.data.entity;

import com.vaadin.flow.component.combobox.ComboBox;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "crimeReport")
@Getter
@Setter
@EqualsAndHashCode

public class CrimeReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotEmpty
//    private ComboBox<String> areaComboBox;
    private String areaComboBox;
//    {
//        areaComboBox.setItems("XXX", "DDD");
//    }
    @NotNull
    private int messageNumber;

//    @NotEmpty
    private LocalDate messageDate;

    @NotNull
    private int caseNumber;

//    @NotEmpty
    private LocalDate caseNumberDate;

    @NotEmpty
    private String message;

//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "crimeReport_id")
//    private Claimant claimant;

//    @NotNull
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "crimeReport_id")
//    private List<Scammer> scammer;

    public CrimeReport() {

    }
}
