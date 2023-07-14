package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity(name = "crimeSummary")
@Getter
@Setter
@EqualsAndHashCode
public class CrimeSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotEmpty
    private String areaComboBox;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "crimeSummary_id")
    private CrimeReport crimeReport = new CrimeReport();

//    @JoinColumn(name = "crimeSummary_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private CrimeCase crimeCase = new CrimeCase();
    private CrimeCase crimeCase;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "crimeSummary_id")
//    @Autowired
    private Claimant claimant = new Claimant();


    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Scammer> scammers = new ArrayList<>();

    {
        Scammer scammer = new Scammer("xxxxxxx");
        scammers.add(scammer);
    }

    public CrimeSummary() {

    }
}
