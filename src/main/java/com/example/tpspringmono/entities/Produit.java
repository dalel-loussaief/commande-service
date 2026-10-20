package com.example.tpspringmono.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class Produit {
    
    private Integer id;

 
    private String reference;

 
    private String description;

    private double price;

    private int stock;

   
}
