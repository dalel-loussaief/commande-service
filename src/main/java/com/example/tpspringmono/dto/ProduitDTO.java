/*package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.CategoryRepo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record ProduitDTO(
        Integer id,
        @NotBlank String reference,
        @Size(max = 100) String description,
        @PositiveOrZero double price,
        @PositiveOrZero int stock,
        Integer categoryId) {

    public static ProduitDTO toDto(Produit p) {
        return new ProduitDTO(
                p.getId(),
                p.getReference(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.getCat() == null ? null : p.getCat().getId());
    }

    public Produit fromDto(CategoryRepo categoryRepo) {
        Produit p = new Produit();
        p.setId(id);
        p.setReference(reference);
        p.setDescription(description);
        p.setPrice(price);
        p.setStock(stock);

        if (categoryId != null) {
            p.setCat(categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Catégorie introuvable : " + categoryId)));
        }
        return p;
    }
}*/