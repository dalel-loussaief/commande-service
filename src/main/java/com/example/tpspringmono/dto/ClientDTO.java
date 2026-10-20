package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Client;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(
        Integer id,
        @NotBlank String nom,
        @NotBlank String prenom,
        @NotBlank @Email String email,
        String telephone,
        @NotNull @Valid AdresseDTO adresse) {

    public static ClientDTO toDto(Client c) {
        return new ClientDTO(
                c.getId(),
                c.getNom(),
                c.getPrenom(),
                c.getEmail(),
                c.getTelephone(),
                AdresseDTO.toDto(c.getAdresse()));
    }

    public Client toEntity() {
        Client c = new Client();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setEmail(email);
        c.setTelephone(telephone);
        c.setAdresse(adresse.toEntity());
        return c;
    }
}
