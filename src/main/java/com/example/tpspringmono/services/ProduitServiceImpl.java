/*
package com.example.tpspringmono.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.tpspringmono.entities.Produit;


import lombok.RequiredArgsConstructor;

@Service
@Transactional

public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepo produitRepo;

    @Override
    public ResponseEntity<Produit> save(Produit product) {

        product.setId(null);

        Produit saved = produitRepo.save(product);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saved);
    }

    @Override
    public ResponseEntity<Produit> update(int id, Produit product) {

        Produit existing = getOrThrow(id);

        existing.setReference(product.getReference());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        Produit updated = produitRepo.save(existing);

        return ResponseEntity.ok(updated);
    }

    @Override
    public void delete(int id) {

        Produit existing = getOrThrow(id);
        produitRepo.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Produit> findById(int id) {

        return ResponseEntity.ok(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAllByCategoryId(int id) {

        return produitRepo.findByCatId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAllByCategoryName(String name) {

        return produitRepo.findByCatNom(name);
    }

    private Produit getOrThrow(int id) {

        return produitRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Produit introuvable : " + id
            ));
    }
}
*/