package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;

public interface PaymentSaleService {

    void registerPayment(Sale sale, PaymentSaleResquestDTO paymentSaleResquestDTO) throws NotAcceptableException;
}
