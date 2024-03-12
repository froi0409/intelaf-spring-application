package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.store.CreateStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.EditStoreRequestDTO;
import com.ayd2.intelafbackend.dto.store.StoreResponseDTO;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.repositories.StoreRepository;
import com.ayd2.intelafbackend.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreResponseDTO createStore(CreateStoreRequestDTO newStore) throws DuplicatedEntityException{
        Optional<Store> isDuplicated = storeRepository.findById(newStore.getIdStore());

        if (isDuplicated.isPresent()) {
            throw new DuplicatedEntityException(String.format("Store with code: %s, is already exists. Try with other code", newStore.getIdStore()));
        }

        Store newStoreEntity = new Store();
        newStoreEntity.setIdStore(newStore.getIdStore());
        newStoreEntity.setName(newStore.getName());
        newStoreEntity.setAddress(newStore.getAddress());
        newStoreEntity.setPhone1(newStore.getPhone1());
        newStoreEntity.setPhone2(newStore.getPhone2());
        newStoreEntity.setEmail(newStore.getEmail());
        newStoreEntity.setOpeningHour(newStore.getOpeningHour());
        newStoreEntity.setClosingHour(newStore.getClosingHour());

        if (newStoreEntity.getPhone2().isEmpty()) {
            newStoreEntity.setPhone2(null);
        }

        newStoreEntity = storeRepository.save(newStoreEntity);

        return new StoreResponseDTO(newStoreEntity);

    }

    @Override
    public StoreResponseDTO editStore(EditStoreRequestDTO storeToUpdate) throws DuplicatedEntityException, EntityNotFoundException {
        Store storeToUpdateEntity = storeRepository.findById(storeToUpdate.getIdStore())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Store with code: %s not found", storeToUpdate.getIdStore())));

        if (!storeToUpdate.getName().equals(storeToUpdateEntity.getName())) {
            storeToUpdateEntity.setName(storeToUpdate.getName());
        }
        if (!storeToUpdate.getAddress().equals(storeToUpdateEntity.getAddress())) {
            storeToUpdateEntity.setAddress(storeToUpdate.getAddress());
        }
        if (!storeToUpdate.getEmail().equals(storeToUpdateEntity.getEmail())) {
            storeToUpdateEntity.setEmail(storeToUpdate.getEmail());
        }
        if (!storeToUpdate.getPhone1().equals(storeToUpdateEntity.getPhone1())) {
            storeToUpdateEntity.setPhone1(storeToUpdate.getPhone1());
        }
        if (storeToUpdate.getPhone2() != null && !storeToUpdate.getPhone2().isBlank() && !storeToUpdate.getPhone2().equals(storeToUpdateEntity.getPhone2())) {
            storeToUpdateEntity.setPhone2(storeToUpdate.getPhone2());
        } else if ((storeToUpdate.getPhone2() == null || storeToUpdate.getPhone2().isBlank()) && storeToUpdateEntity.getPhone2() != null) {
            storeToUpdateEntity.setPhone2(null);
        }
        if (storeToUpdate.getOpeningHour() != null && !storeToUpdate.getOpeningHour().equals(storeToUpdateEntity.getOpeningHour())) {
            storeToUpdateEntity.setOpeningHour(storeToUpdate.getOpeningHour());
        } else if (storeToUpdate.getOpeningHour() == null && storeToUpdateEntity.getOpeningHour() != null) {
            storeToUpdateEntity.setOpeningHour(null);
        }
        if (storeToUpdate.getClosingHour() != null && !storeToUpdate.getClosingHour().equals(storeToUpdateEntity.getClosingHour())) {
            storeToUpdateEntity.setClosingHour(storeToUpdate.getClosingHour());
        } else if (storeToUpdate.getClosingHour() == null && storeToUpdateEntity.getClosingHour() != null) {
            storeToUpdateEntity.setClosingHour(null);
        }

        storeRepository.save(storeToUpdateEntity);

        return new StoreResponseDTO(storeToUpdateEntity);
    }

    @Override
    public List<StoreResponseDTO> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreResponseDTO::new)
                .collect(Collectors.toList());
    }

    public StoreResponseDTO findById(String idStore) throws EntityNotFoundException {
        Store storeEntity = storeRepository.findById(idStore)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Store with code: %s not found", idStore)));
        return new StoreResponseDTO(storeEntity);
    }

}
