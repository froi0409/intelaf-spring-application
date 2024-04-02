package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.order.OrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.OrderRequestUpdateStatusDTO;
import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.customer.TrakingOrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.DeliveryOrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.OrderAllFeatureResponseDTO;
import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductResponseDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderResponseDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.projectioninterface.order.DeliveryOrderProjection;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.repositories.OrderRepository;
import com.ayd2.intelafbackend.repositories.StoreRepository;
import com.ayd2.intelafbackend.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final PaymentOrderService paymentOrderService;
    private final OrderHasProductService orderHasProductService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, StoreRepository storeRepository, PaymentOrderService paymentOrderService, OrderHasProductService orderHasProductService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.paymentOrderService = paymentOrderService;
        this.orderHasProductService = orderHasProductService;
        this.productService = productService;
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
            //Remove from the inventary
            productService.updateStock(orderHasProductRequestDTO.getProductId(),newOrder.getIdStoreShipping(),orderHasProductRequestDTO.getQuantity());
        }
        return new OrderResponseDTO(newOrder);
    }

    @Override
    public List<DeliveryOrderResponseDTO> findDeliveryOrdersByReceiveStore(String idStoreReceive) {
        List<DeliveryOrderProjection> projections = orderRepository.findDeliveryOrdersByReceiveStore(idStoreReceive);
        List<DeliveryOrderResponseDTO> dtos = new ArrayList<>();
        for (DeliveryOrderProjection projection : projections) {
            DeliveryOrderResponseDTO dto = new DeliveryOrderResponseDTO(projection);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DeliveryOrderResponseDTO> findDeliveryOrdersByShippingStore(String idStoreShipping) {
        List<DeliveryOrderProjection> projections = orderRepository.findDeliveryOrdersByShippingStore(idStoreShipping);
        List<DeliveryOrderResponseDTO> deliverys = new ArrayList<>();
        for (DeliveryOrderProjection projection : projections) {
            DeliveryOrderResponseDTO dto = new DeliveryOrderResponseDTO(projection);
            deliverys.add(dto);
        }
        return deliverys;
    }

    @Override
    public OrderResponseDTO updateStatus(OrderRequestUpdateStatusDTO orderRequestUpdateStatusDTO) throws EntityNotFoundException {
        Order order = orderRepository.findById(orderRequestUpdateStatusDTO.getIdOrder())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with code %o not found", orderRequestUpdateStatusDTO.getIdOrder())) );
        order.setStatus(orderRequestUpdateStatusDTO.getStatus());
        if(orderRequestUpdateStatusDTO.getDateEntry() != null) {
            order.setDateEntry(orderRequestUpdateStatusDTO.getDateEntry());
        }
        order = orderRepository.save(order);
        return new OrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO findById(Long idOrder) throws EntityNotFoundException {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with code %o not found", idOrder)) );
        return new OrderResponseDTO(order);

    }
    @Override
    public OrderAllFeatureResponseDTO findByIdWithEstimateDelivery(Long idOrder) throws EntityNotFoundException {
        DeliveryOrderProjection deliveryOrderProjection = orderRepository.findByIdWithEstimateDelivery(idOrder)
                .orElseThrow( () -> new EntityNotFoundException(String.format("Order with code %o not found", idOrder)));
        List<PaymentOrderResponseDTO> paymentOrderProjections = paymentOrderService.findPaymentOrdersByOrderId(deliveryOrderProjection.getId_order());
        List<OrderHasProductResponseDTO> orderHasProductProjections = orderHasProductService.findProductsOrderByIdOrder(deliveryOrderProjection.getId_order());

        return new OrderAllFeatureResponseDTO(deliveryOrderProjection,orderHasProductProjections,paymentOrderProjections);
    }

    @Override
    public List<TrakingOrderResponseDTO> findOrdersByCustomerId(Long idCustomer) throws EntityNotFoundException{
        return orderRepository.findOrdersByCustomerId(idCustomer)
                .stream()
                .map(TrakingOrderResponseDTO :: new)
                .collect(Collectors.toList());
    }

}
