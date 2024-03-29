package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.sale.SaleRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface SaleService {

    List<SaleResponseDTO> findAll();

    SaleResponseDTO registerSale(SaleRequestDTO saleRequestDTO) throws EntityNotFoundException, NotAcceptableException;
}
