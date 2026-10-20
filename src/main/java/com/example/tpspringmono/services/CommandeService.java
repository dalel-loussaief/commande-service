package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.CommandeDTO;
import com.example.tpspringmono.dto.CommandeRequest;

import java.util.List;

public interface CommandeService {
    CommandeDTO create(CommandeRequest request);

    CommandeDTO findById(int id);

    List<CommandeDTO> findByClientId(int clientId);
}
