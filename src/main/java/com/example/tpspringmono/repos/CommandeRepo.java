package com.example.tpspringmono.repos;

import com.example.tpspringmono.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Integer> {
    // "client" est le champ de Commande : client.id
    List<Commande> findByClientId(int clientId);

    boolean existsByClientId(int clientId);
}
