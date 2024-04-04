package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductResponseDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.orders.OrderHasProduct;
import com.ayd2.intelafbackend.entities.orders.OrderHasProductPK;
import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.projectioninterface.order.orderhasproducts.OrderHasProductProjection;
import com.ayd2.intelafbackend.repositories.OrderHasProductRepository;
import com.ayd2.intelafbackend.repositories.ProductRepository;
import com.ayd2.intelafbackend.services.OrderHasProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHasProductServiceImpl implements OrderHasProductService {
    private final OrderHasProductRepository orderHasProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderHasProductServiceImpl(OrderHasProductRepository orderHasProductRepository, ProductRepository productRepository) {
        this.orderHasProductRepository = orderHasProductRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void registerProduct(Order order, OrderHasProductRequestDTO orderHasProductRequestDTO) throws NotAcceptableException, EntityNotFoundException {
        Product product = productRepository.findById(orderHasProductRequestDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("product not found"));

        // create the PK
        OrderHasProductPK orderHasProductPK = new OrderHasProductPK();
        orderHasProductPK.setOrderId(order.getIdOrder());
        orderHasProductPK.setProductId(product.getIdProduct());

        //create the register
        OrderHasProduct newOrderHasProduct = new OrderHasProduct();
        newOrderHasProduct.setQuantiy(orderHasProductRequestDTO.getQuantity());
        newOrderHasProduct.setOrderHasProductPK(orderHasProductPK);
        newOrderHasProduct.setProduct(product);
        newOrderHasProduct.setOrder(order);
        orderHasProductRepository.save(newOrderHasProduct);

    }

    @Override
    public List<OrderHasProductResponseDTO> findProductsOrderByIdOrder(Long orderIdOrder) throws EntityNotFoundException {
        return orderHasProductRepository.findProductsOrderByIdOrder(orderIdOrder)
                .stream()
                .map(OrderHasProductResponseDTO :: new)
                .collect(Collectors.toList());
    }
}
