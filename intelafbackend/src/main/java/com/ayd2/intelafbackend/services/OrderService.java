package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();
}
