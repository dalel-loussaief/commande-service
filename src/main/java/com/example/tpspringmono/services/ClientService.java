package com.example.tpspringmono.services;

import com.example.tpspringmono.dto.ClientDTO;

public interface ClientService {
    ClientDTO save(ClientDTO dto);

    ClientDTO findById(int id);

    ClientDTO update(int id, ClientDTO dto);

    void delete(int id);
}
