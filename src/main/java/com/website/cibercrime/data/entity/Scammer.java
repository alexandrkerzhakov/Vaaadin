package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "scammer")
@Getter
@Setter
@EqualsAndHashCode
public class Scammer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String secondName;

    @NotEmpty
    private String fatherName;

    @Email
    private String email;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "crimeReport_id")
    private CrimeReport crimeReportList;

    @NotNull
    @OneToMany
    private List<Phone> phoneList;

}

