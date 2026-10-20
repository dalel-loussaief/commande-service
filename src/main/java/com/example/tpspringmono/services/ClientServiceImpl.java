package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.ClientDTO;
import com.example.tpspringmono.entities.Client;
import com.example.tpspringmono.repos.ClientRepo;
import com.example.tpspringmono.repos.CommandeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final CommandeRepo commandeRepo;

    @Override
    public ClientDTO save(ClientDTO dto) {
        // Règle 5 : l'email d'un client est unique
        if (clientRepo.existsByEmail(dto.email())) {
            throw emailConflict(dto.email());
        }
        // Le client est créé avec son adresse (cascade)
        return ClientDTO.toDto(clientRepo.save(dto.toEntity()));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(int id) {
        return ClientDTO.toDto(getOrThrow(id));
    }

    @Override
    public ClientDTO update(int id, ClientDTO dto) {
        Client existing = getOrThrow(id);

        if (clientRepo.existsByEmailAndIdNot(dto.email(), id)) {
            throw emailConflict(dto.email());
        }

        existing.setNom(dto.nom());
        existing.setPrenom(dto.prenom());
        existing.setEmail(dto.email());
        existing.setTelephone(dto.telephone());

        if (existing.getAdresse() == null) {
            existing.setAdresse(dto.adresse().toEntity());
        } else {
            dto.adresse().applyTo(existing.getAdresse());
        }

        return ClientDTO.toDto(clientRepo.save(existing));
    }

    @Override
    public void delete(int id) {
        Client existing = getOrThrow(id);

        // Évite la violation de clé étrangère avec les commandes existantes
        if (commandeRepo.existsByClientId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Impossible de supprimer le client " + id + " : il a des commandes");
        }

        clientRepo.delete(existing); // l'adresse est supprimée en cascade
    }

    private Client getOrThrow(int id) {
        return clientRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client introuvable : " + id));
    }

    private ResponseStatusException emailConflict(String email) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé : " + email);
    }
}
