package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity(name = "claimant")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class Claimant {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ToString.Include
    @NotEmpty
    private String firstName;

    @ToString.Include
    @NotEmpty
    private String secondName;

    @ToString.Include
    @NotEmpty
    private String fatherName;

    public Claimant() {
        this.firstName = "defaultfirstName";
        this.secondName = "defaultsecondName";
        this.fatherName = "defaultfatherName";
    }

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "claimant_id")
    private List<Phone> phones = new ArrayList<>();
}

