package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.store.CreateStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.EditStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;

import java.util.List;

public interface StoreService {

    StoreResponseDTO createStore(CreateStoreRequestDTO newStore) throws DuplicatedEntityException;

    StoreResponseDTO editStore(EditStoreRequestDTO storeToEdit) throws DuplicatedEntityException, EntityNotFoundException;

    StoreResponseDTO findById(String idStore) throws EntityNotFoundException;

    List<StoreResponseDTO> findAll();

}
