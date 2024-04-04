package com.ayd2.intelafbackend.dto.order;

import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class OrderRequestDTO {
    String nit;
    String idStoreShipping;
    String idStoreReceive;
    LocalDateTime dateDeparture;
    BigDecimal total;
    String status;
    List<OrderHasProductRequestDTO> products;
    List<PaymentOrderRequestDTO> payments;
}
