package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.store.CreateShippingTimeRequestDTO;
import com.ayd2.intelafbackend.dto.store.EditShippingTimeRequestDTO;
import com.ayd2.intelafbackend.dto.store.ShippingTimeResponseDTO;
import com.ayd2.intelafbackend.entities.store.ShippingTime;
import com.ayd2.intelafbackend.entities.store.ShippingTimeId;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.repositories.ShippingTimeRepository;
import com.ayd2.intelafbackend.repositories.StoreRepository;
import com.ayd2.intelafbackend.services.ShippingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShippingTimeServiceImpl implements ShippingTimeService {

    private StoreRepository storeRepository;
    private ShippingTimeRepository shippingTimeRepository;

    @Autowired
    public ShippingTimeServiceImpl(ShippingTimeRepository shippingTimeRepository, StoreRepository storeRepository) {
        this.shippingTimeRepository = shippingTimeRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public ShippingTimeResponseDTO createShippingTime(CreateShippingTimeRequestDTO newShippingTime) throws EntityNotFoundException {
        if (existsStores(newShippingTime.getIdStore1(), newShippingTime.getIdStore2())) {
            ShippingTime shippingTimeEntity = new ShippingTime();
            shippingTimeEntity.setIdStore1(newShippingTime.getIdStore1());
            shippingTimeEntity.setIdStore2(newShippingTime.getIdStore2());
            shippingTimeEntity.setTime(newShippingTime.getTime());

            shippingTimeEntity = shippingTimeRepository.save(shippingTimeEntity);

            return new ShippingTimeResponseDTO(shippingTimeEntity);

        } else {
            throw new EntityNotFoundException("One or both stores do not exists");
        }
    }

    @Override
    public ShippingTimeResponseDTO editShippingTime(EditShippingTimeRequestDTO shippingTimeToEdit) throws EntityNotFoundException {
        String idStore1 = shippingTimeToEdit.getIdStore1();
        String idStore2 = shippingTimeToEdit.getIdStore2();
        if (existsStores(idStore1, idStore2)) {
            ShippingTime shippingTimeEntity = shippingTimeRepository.findById(new ShippingTimeId(idStore1, idStore2))
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Shipping time between %s and %s does not exists", idStore1, idStore2)));

            if (!Objects.equals(shippingTimeEntity.getTime(), shippingTimeToEdit.getTime())) {
                shippingTimeEntity.setTime(shippingTimeToEdit.getTime());
            }

            shippingTimeRepository.save(shippingTimeEntity);

            return new ShippingTimeResponseDTO(shippingTimeEntity);
        } else {
            throw  new EntityNotFoundException("One or both stores do not exists");
        }
    }

    @Override
    public List<ShippingTimeResponseDTO> findAll() {
        return shippingTimeRepository.findAll()
                .stream()
                .map(ShippingTimeResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShippingTimeResponseDTO> findAllByOrigin(String idStore) {
        return shippingTimeRepository.findAllByIdStore1(idStore)
                .stream()
                .map(ShippingTimeResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShippingTimeResponseDTO> findAllByDestiny(String idStore) {
        return shippingTimeRepository.findAllByIdStore2(idStore)
                .stream()
                .map(ShippingTimeResponseDTO::new)
                .collect(Collectors.toList());
    }

    private boolean existsStores(String storeId1, String storeId2) {
        Optional<Store> store1 = storeRepository.findById(storeId1);
        Optional<Store> store2 = storeRepository.findById(storeId2);

        return (store1.isPresent() && store2.isPresent());
    }

}
