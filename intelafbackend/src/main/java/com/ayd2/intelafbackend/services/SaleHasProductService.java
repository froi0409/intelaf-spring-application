package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.dto.sale.salehasproduct.SaleHasProductRequestDTO;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import jakarta.persistence.EntityNotFoundException;

public interface SaleHasProductService {
    void registerProduct(Sale sale, SaleHasProductRequestDTO saleHasProductRequestDTO) throws NotAcceptableException, EntityNotFoundException;
}
