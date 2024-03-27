package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.order.OrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.DeliveryOrderResponseDTO;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @PostMapping("create-order")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws NotAcceptableException, EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(orderRequestDTO));
    }

    @GetMapping("/receiveStore/{idStoreReceive}")
    public ResponseEntity<List<DeliveryOrderResponseDTO>> findDeliveryOrdersByReceiveStore(@PathVariable String idStoreReceive) {
        List<DeliveryOrderResponseDTO> orders = orderService.findDeliveryOrdersByReceiveStore(idStoreReceive);
        return ResponseEntity.ok(orders);
    }
}
