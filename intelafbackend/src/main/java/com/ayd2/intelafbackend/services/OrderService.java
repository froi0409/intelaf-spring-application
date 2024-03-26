package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.OrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws EntityNotFoundException, NotAcceptableException;
}
