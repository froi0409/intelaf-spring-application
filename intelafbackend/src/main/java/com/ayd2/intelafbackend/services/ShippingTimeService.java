package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.store.CreateShippingTimeRequestDTO;
import com.ayd2.intelafbackend.dto.store.EditShippingTimeRequestDTO;
import com.ayd2.intelafbackend.dto.store.ShippingTimeResponseDTO;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;

import java.util.List;

public interface ShippingTimeService {
    ShippingTimeResponseDTO createShippingTime(CreateShippingTimeRequestDTO newShippingTime) throws EntityNotFoundException, DuplicatedEntityException;

    ShippingTimeResponseDTO editShippingTime(EditShippingTimeRequestDTO shippingTimeToEdit) throws EntityNotFoundException;

    List<ShippingTimeResponseDTO> findAll();

    List<ShippingTimeResponseDTO> findAllByOrigin(String idStore);

    List<ShippingTimeResponseDTO> findAllByDestiny(String idStore);
}
