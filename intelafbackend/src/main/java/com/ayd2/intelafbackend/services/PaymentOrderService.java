package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;

public interface PaymentOrderService {

    void registerPayment(Order order, PaymentOrderRequestDTO paymentOrderRequestDTO) throws NotAcceptableException;
}
