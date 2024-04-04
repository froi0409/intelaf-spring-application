package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.order.OrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.OrderRequestUpdateStatusDTO;
import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.customer.TrakingOrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.DeliveryOrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.OrderAllFeatureResponseDTO;
import com.ayd2.intelafbackend.dto.order.reports.OrderInTimeStatusRouteResponseDTO;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/find-order-id/{idOrder}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<OrderResponseDTO> findOrderId(@PathVariable Long idOrder) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.findById(idOrder));
    }

    @PostMapping("create-order")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws NotAcceptableException, EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(orderRequestDTO));
    }

    @GetMapping("/receiveStore/{idStoreReceive}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<DeliveryOrderResponseDTO>> findDeliveryOrdersByReceiveStore(@PathVariable String idStoreReceive) {
        List<DeliveryOrderResponseDTO> orders = orderService.findDeliveryOrdersByReceiveStore(idStoreReceive);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/shippingStore/{idStoreShipping}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<DeliveryOrderResponseDTO>> findDeliveryOrdersByShippingStore(@PathVariable String idStoreShipping) {
        List<DeliveryOrderResponseDTO> orders = orderService.findDeliveryOrdersByShippingStore(idStoreShipping);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("updateStatus")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<OrderResponseDTO> updateStatus(@RequestBody OrderRequestUpdateStatusDTO orderRequestUpdateStatusDTO) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateStatus(orderRequestUpdateStatusDTO));
    }

    @GetMapping("/find-all-feature-id/{idOrder}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<OrderAllFeatureResponseDTO> findByIdWithEstimateDelivery(@PathVariable Long idOrder) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.findByIdWithEstimateDelivery(idOrder));
    }

    @GetMapping("/find-order-customer-username/{userUsername}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<TrakingOrderResponseDTO>> findOrdersByCustomerId(@PathVariable String userUsername) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.findOrdersByCustomerUsername(userUsername));
    }

}
