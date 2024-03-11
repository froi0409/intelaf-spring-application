package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.store.CreateStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;

import java.util.List;

public interface StoreService {

    StoreResponseDTO createStore(CreateStoreRequestDTO newStore) throws DuplicatedEntityException;

    List<StoreResponseDTO> findAll();

}
