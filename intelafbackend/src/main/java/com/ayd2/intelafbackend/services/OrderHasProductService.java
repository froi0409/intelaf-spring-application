package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;

public interface OrderHasProductService {

    void registerProduct(Order order, OrderHasProductRequestDTO orderHasProductRequestDTO) throws NotAcceptableException, EntityNotFoundException;
}
