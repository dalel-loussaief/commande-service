
package com.example.tpspringmono.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tpspringmono.entities.Produit;

import jakarta.validation.Valid;

@FeignClient(
    name = "catalogue-service",
    url = "http://localhost:9091/api/produits"
)
public interface ProduitFeign {

    @PostMapping
    ResponseEntity<Produit> createProduit(
        @RequestBody @Valid Produit produit
    );

    @GetMapping("/{id}")
    Produit getProduitById(@PathVariable("id") int id);

    @PutMapping("/{id}")
    ResponseEntity<Produit> updateProduit(
        @PathVariable("id") int id,
        @RequestBody @Valid Produit produit
    );
}