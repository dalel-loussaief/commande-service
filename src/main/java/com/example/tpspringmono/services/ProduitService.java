package com.example.tpspringmono.services;

import com.example.tpspringmono.entities.Produit;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProduitService {
    ResponseEntity<Produit>  save (Produit product);
    ResponseEntity<Produit> update(int id,Produit product);
    void delete(int id);
    ResponseEntity<Produit> findById(int id);
    List<Produit> findAllByCategoryId(int id);
    List<Produit> findAllByCategoryName(String name);
}
