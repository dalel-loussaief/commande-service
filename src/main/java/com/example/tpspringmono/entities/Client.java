package com.example.tpspringmono.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    // One-to-One : l'adresse est créée, modifiée et supprimée avec le client
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    // One-to-Many bidirectionnel avec Commande (côté inverse)
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<>();
}
