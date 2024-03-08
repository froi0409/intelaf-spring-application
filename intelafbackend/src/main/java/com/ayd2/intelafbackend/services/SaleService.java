package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;

import java.util.List;

public interface SaleService {

    List<SaleResponseDTO> findAll();
}
