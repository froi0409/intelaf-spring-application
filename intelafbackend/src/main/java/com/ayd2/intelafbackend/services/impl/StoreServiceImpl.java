package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.store.CreateStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.repositories.StoreRepository;
import com.ayd2.intelafbackend.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreResponseDTO createStore(CreateStoreRequestDTO newStore) {
        Store newStoreEntity = new Store();
        newStoreEntity.setIdStore(newStore.getIdStore());
        newStoreEntity.setName(newStore.getName());
        newStoreEntity.setAddress(newStore.getAddress());
        newStoreEntity.setPhone1(newStore.getPhone1());
        newStoreEntity.setPhone2(newStore.getPhone2());
        newStoreEntity.setEmail(newStore.getEmail());
        newStoreEntity.setOpeningHour(newStore.getOpeningHour());
        newStoreEntity.setClosingHour(newStore.getClosingHour());

        newStoreEntity = storeRepository.save(newStoreEntity);

        return new StoreResponseDTO(newStoreEntity);

    }

    @Override
    public List<StoreResponseDTO> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreResponseDTO::new)
                .collect(Collectors.toList());
    }

}
