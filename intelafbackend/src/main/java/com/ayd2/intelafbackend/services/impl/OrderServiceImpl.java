package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.order.OrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.orders.OrderHasProduct;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.repositories.OrderRepository;
import com.ayd2.intelafbackend.repositories.StoreRepository;
import com.ayd2.intelafbackend.services.CustomerService;
import com.ayd2.intelafbackend.services.OrderHasProductService;
import com.ayd2.intelafbackend.services.OrderService;
import com.ayd2.intelafbackend.services.PaymentOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final PaymentOrderService paymentOrderService;
    private final OrderHasProductService orderHasProductService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, StoreRepository storeRepository, PaymentOrderService paymentOrderService, OrderHasProductService orderHasProductService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.paymentOrderService = paymentOrderService;
        this.orderHasProductService = orderHasProductService;
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws EntityNotFoundException, NotAcceptableException {
        Customer customer = customerRepository.findByNit(orderRequestDTO.getNit())
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("customer not found"));

        Store storeShipping = storeRepository.findById(orderRequestDTO.getIdStoreShipping())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Store with code: %s not found", orderRequestDTO.getIdStoreShipping())));

        Store storeReceive = storeRepository.findById(orderRequestDTO.getIdStoreReceive())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Store with code: %s not found", orderRequestDTO.getIdStoreReceive())));

        Order newOrder = new Order();

        newOrder.setCustomer(customer);
        newOrder.setIdCustomer(customer.getUser().getIdUser());
        newOrder.setShippingStore(storeShipping);
        newOrder.setIdStoreShipping(storeShipping.getIdStore());
        newOrder.setReceivingStore(storeReceive);
        newOrder.setIdStoreReceive(storeReceive.getIdStore());
        newOrder.setStatus(orderRequestDTO.getStatus());
        newOrder.setDateDeparture(orderRequestDTO.getDateDeparture());
        newOrder.setTotal(orderRequestDTO.getTotal());

        newOrder = orderRepository.save(newOrder);

        BigDecimal usedCredit = BigDecimal.valueOf(0);
        //Add payment order
        for (PaymentOrderRequestDTO paymentOrderRequestDTO : orderRequestDTO.getPayments()) {
            paymentOrderService.registerPayment(newOrder, paymentOrderRequestDTO);
            if (paymentOrderRequestDTO.getType().equalsIgnoreCase("credit")) {
                usedCredit = usedCredit.add(BigDecimal.valueOf(paymentOrderRequestDTO.getAmount()));

            }
        }

        // UPDATE CREDITS
        if (usedCredit.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal newCredits = customer.getCredit().subtract(usedCredit);
            //  actualizar la entidad 'customer'
            customer.setCredit(newCredits);
            // 'customer' tiene el nuevo crédito actualizado
            customerRepository.save(customer);
        }

        //Add products to order
        for (OrderHasProductRequestDTO orderHasProductRequestDTO: orderRequestDTO.getProducts()) {
            orderHasProductService.registerProduct(newOrder, orderHasProductRequestDTO);
        }

        return new OrderResponseDTO(newOrder);
    }


}
