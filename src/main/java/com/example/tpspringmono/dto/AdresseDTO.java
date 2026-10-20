package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Adresse;
import jakarta.validation.constraints.NotBlank;

public record AdresseDTO(
        Integer id,
        @NotBlank String rue,
        @NotBlank String ville,
        @NotBlank String codePostal,
        @NotBlank String pays) {

    public static AdresseDTO toDto(Adresse a) {
        if (a == null) {
            return null;
        }
        return new AdresseDTO(a.getId(), a.getRue(), a.getVille(), a.getCodePostal(), a.getPays());
    }

    public Adresse toEntity() {
        Adresse a = new Adresse();
        applyTo(a);
        return a;
    }

    /** Copie les valeurs du DTO dans une adresse existante (l'id est conservé). */
    public void applyTo(Adresse a) {
        a.setRue(rue);
        a.setVille(ville);
        a.setCodePostal(codePostal);
        a.setPays(pays);
    }
}
