package com.example.tpspringmono.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

/**
 * Corps de la requête de création d'une commande.
 * Le prix unitaire et le montant total ne sont volontairement pas fournis par le client.
 */
public record CommandeRequest(
        @NotNull Integer clientId,
        @NotEmpty @Valid List<LigneRequest> lignes) {

    public record LigneRequest(
            @NotNull Integer produitId,
            @Positive int quantite) {
    }
}
