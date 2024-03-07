package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;

import java.util.List;

public interface StoreService {

    List<StoreResponseDTO> findAll();

}
