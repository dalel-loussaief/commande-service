
package com.example.tpspringmono.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.tpspringmono.dto.CommandeDTO;
import com.example.tpspringmono.dto.CommandeRequest;
import com.example.tpspringmono.entities.Client;
import com.example.tpspringmono.entities.Commande;
import com.example.tpspringmono.entities.LigneCommande;
import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.entities.StatutCommande;
import com.example.tpspringmono.feign.ProduitFeign;
import com.example.tpspringmono.repos.ClientRepo;
import com.example.tpspringmono.repos.CommandeRepo;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private  CommandeRepo commandeRepo;
    private  ClientRepo clientRepo;
    private  ProduitFeign produitFeign;

    @Override
    public CommandeDTO create(CommandeRequest request) {

        Client client = clientRepo.findById(request.clientId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Client introuvable : " + request.clientId()
            ));

        if (request.lignes() == null || request.lignes().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Une commande doit contenir au moins une ligne."
            );
        }

        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(LocalDateTime.now());
        commande.setStatut(StatutCommande.CREEE);

        double total = 0;

        for (CommandeRequest.LigneRequest ligneRequest : request.lignes()) {

            if (ligneRequest.quantite() <= 0) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La quantité doit être supérieure à zéro."
                );
            }

            Produit produit = getProduit(ligneRequest.produitId());

            if (produit.getStock() < ligneRequest.quantite()) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Stock insuffisant pour le produit "
                        + produit.getReference()
                        + " (demandé : " + ligneRequest.quantite()
                        + ", disponible : " + produit.getStock() + ")"
                );
            }

            produit.setStock(
                produit.getStock() - ligneRequest.quantite()
            );

            try {
                produitFeign.updateProduit(
                    produit.getId(),
                    produit
                );
            } catch (FeignException e) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Erreur lors de la mise à jour du stock du produit "
                        + produit.getId(),
                    e
                );
            }

            LigneCommande ligne = new LigneCommande();
            ligne.setCommande(commande);
            ligne.setProduit(produit);
            ligne.setQuantite(ligneRequest.quantite());
            ligne.setPrixUnitaire(produit.getPrice());

            commande.getLignes().add(ligne);

            total += ligneRequest.quantite() * produit.getPrice();
        }

        commande.setMontantTotal(total);

        return CommandeDTO.toDto(commandeRepo.save(commande));
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeDTO findById(int id) {

        Commande commande = commandeRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Commande introuvable : " + id
            ));

        return CommandeDTO.toDto(commande);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommandeDTO> findByClientId(int clientId) {

        if (!clientRepo.existsById(clientId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Client introuvable : " + clientId
            );
        }

        return commandeRepo.findByClientId(clientId)
            .stream()
            .map(CommandeDTO::toDto)
            .toList();
    }

    private Produit getProduit(int id) {

        try {
            return produitFeign.getProduitById(id);

        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Produit introuvable : " + id
            );

        } catch (FeignException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Impossible de récupérer le produit : " + id,
                e
            );
        }
    }
}
