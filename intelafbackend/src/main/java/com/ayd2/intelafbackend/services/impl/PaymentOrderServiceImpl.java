package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderResponseDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.orders.PaymentOrder;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.projectioninterface.order.paymentorder.PaymentOrderProjection;
import com.ayd2.intelafbackend.repositories.PaymentOrderRepository;
import com.ayd2.intelafbackend.services.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private final PaymentOrderRepository paymentOrderRepository;

    @Autowired
    public PaymentOrderServiceImpl(PaymentOrderRepository paymentOrderRepository) {
        this.paymentOrderRepository = paymentOrderRepository;
    }

    @Override
    public void registerPayment(Order order, PaymentOrderRequestDTO paymentOrderRequestDTO) throws NotAcceptableException {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setOrder(order);
        paymentOrder.setType(paymentOrderRequestDTO.getType());
        paymentOrder.setAmount(paymentOrderRequestDTO.getAmount());
        paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public List<PaymentOrderResponseDTO> findPaymentOrdersByOrderId(Long orderIdOrder) throws EntityNotFoundException {
        return paymentOrderRepository.findPaymentOrdersByOrderId(orderIdOrder)
                .stream()
                .map(PaymentOrderResponseDTO :: new)
                .collect(Collectors.toList());
    }
}
