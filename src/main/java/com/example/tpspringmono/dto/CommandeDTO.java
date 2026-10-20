package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Commande;
import com.example.tpspringmono.entities.LigneCommande;
import com.example.tpspringmono.entities.StatutCommande;

import java.time.LocalDateTime;
import java.util.List;

public record CommandeDTO(
        Integer id,
        LocalDateTime dateCommande,
        StatutCommande statut,
        double montantTotal,
        Integer clientId,
        List<LigneDTO> lignes) {

    public record LigneDTO(Integer id, Integer produitId, int quantite, double prixUnitaire) {

        public static LigneDTO toDto(LigneCommande l) {
            return new LigneDTO(l.getId(), l.getProduit().getId(), l.getQuantite(), l.getPrixUnitaire());
        }
    }

    public static CommandeDTO toDto(Commande c) {
        return new CommandeDTO(
                c.getId(),
                c.getDateCommande(),
                c.getStatut(),
                c.getMontantTotal(),
                c.getClient().getId(),
                c.getLignes().stream().map(LigneDTO::toDto).toList());
    }
}
