package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderResponseDTO;
import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.projectioninterface.order.paymentorder.PaymentOrderProjection;

import java.util.List;

public interface PaymentOrderService {

    void registerPayment(Order order, PaymentOrderRequestDTO paymentOrderRequestDTO) throws NotAcceptableException;

    List<PaymentOrderResponseDTO> findPaymentOrdersByOrderId(Long orderIdOrder) throws EntityNotFoundException;
}
