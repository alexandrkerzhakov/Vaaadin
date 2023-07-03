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
//@Getter
//@Setter
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

    public String getAreaComboBox() {
        return areaComboBox;
    }

    public void setAreaComboBox(String areaComboBox) {
        this.areaComboBox = areaComboBox;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDate messageDate) {
        this.messageDate = messageDate;
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public LocalDate getCaseNumberDate() {
        return caseNumberDate;
    }

    public void setCaseNumberDate(LocalDate caseNumberDate) {
        this.caseNumberDate = caseNumberDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Claimant getClaimant() {
        return claimant;
    }

    public void setClaimant(Claimant claimant) {
        this.claimant = claimant;
    }


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
