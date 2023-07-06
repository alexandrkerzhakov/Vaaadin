package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crimeReport_id")
//    @Autowired
    private Claimant claimant = new Claimant();

    //    public CrimeReport() {
//        this.claimant.setFirstName("");
//        this.claimant.setSecondName("");
//        this.claimant.setFatherName("");
//    }

    //    @NotNull
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "crimeReport_id")
//    private List<Scammer> scammer;

    public CrimeReport() {

    }
}
