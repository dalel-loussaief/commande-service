/*package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.ProduitDTO;
import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.CategoryRepo;
import com.example.tpspringmono.services.ProduitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")

public class ProduitController {

    private final ProduitService produitService;
    private final CategoryRepo categoryRepo;

    @PostMapping
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody @Valid ProduitDTO dto) {
        Produit saved = produitService.save(dto.fromDto(categoryRepo)).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(ProduitDTO.toDto(saved));
    }

    @GetMapping("/{id}")
    public ProduitDTO getProduitById(@PathVariable int id) {
        return ProduitDTO.toDto(produitService.findById(id).getBody());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable int id,
                                                    @RequestBody @Valid ProduitDTO dto) {
        Produit updated = produitService.update(id, dto.fromDto(categoryRepo)).getBody();
        return ResponseEntity.ok(ProduitDTO.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduit(@PathVariable int id) {
        produitService.delete(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProduitDTO> getByCategoryId(@PathVariable int categoryId) {
        return produitService.findAllByCategoryId(categoryId).stream()
                .map(ProduitDTO::toDto)
                .toList();
    }

    @GetMapping("/category/name/{name}")
    public List<ProduitDTO> getByCategoryName(@PathVariable String name) {
        return produitService.findAllByCategoryName(name).stream()
                .map(ProduitDTO::toDto)
                .toList();
    }
}*/