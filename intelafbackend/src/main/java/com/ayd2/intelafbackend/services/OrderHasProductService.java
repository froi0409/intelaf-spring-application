package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductResponseDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.projectioninterface.order.orderhasproducts.OrderHasProductProjection;

import java.util.List;

public interface OrderHasProductService {

    void registerProduct(Order order, OrderHasProductRequestDTO orderHasProductRequestDTO) throws NotAcceptableException, EntityNotFoundException;

    List<OrderHasProductResponseDTO> findProductsOrderByIdOrder(Long orderIdOrder) throws EntityNotFoundException;
}
