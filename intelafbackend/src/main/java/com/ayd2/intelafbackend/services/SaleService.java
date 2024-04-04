package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.sale.SaleOrderRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.dto.sale.reports.SaleByIdCustomerResponseDTO;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface SaleService {

    List<SaleResponseDTO> findAll();

    SaleResponseDTO registerSale(SaleRequestDTO saleRequestDTO) throws EntityNotFoundException, NotAcceptableException;
    SaleResponseDTO registerSaleFromOrder(SaleOrderRequestDTO saleRequestDTO) throws EntityNotFoundException, NotAcceptableException;
    List<SaleByIdCustomerResponseDTO> salesByIdCustomer(Long idCustomer);
}
