package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResponseDTO;
import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import java.util.List;

public interface PaymentSaleService {

    void registerPayment(Sale sale, PaymentSaleResquestDTO paymentSaleResquestDTO) throws NotAcceptableException;
    List<PaymentSaleResponseDTO> findByPaymentSaleIdSale(Long saleId);
}
