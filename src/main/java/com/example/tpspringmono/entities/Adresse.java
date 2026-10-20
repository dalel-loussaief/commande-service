package com.example.tpspringmono.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adresses")
@Getter
@Setter
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
}
