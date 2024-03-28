package com.ayd2.intelafbackend.dto.order.deliveryorder;

import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductRequestDTO;
import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductResponseDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderResponseDTO;
import com.ayd2.intelafbackend.projectioninterface.order.DeliveryOrderProjection;
import com.ayd2.intelafbackend.projectioninterface.order.orderhasproducts.OrderHasProductProjection;
import com.ayd2.intelafbackend.projectioninterface.order.paymentorder.PaymentOrderProjection;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class OrderAllFeatureResponseDTO {
    Long idOrder;
    String idStoreShipping;
    String idStoreReceive;
    LocalDateTime dateDeparture;
    LocalDateTime dateEntry;
    LocalDateTime estimatedDeliveryDate;
    BigDecimal total;
    String status;
    List<OrderHasProductResponseDTO> products;
    List<PaymentOrderResponseDTO> payments;


    public OrderAllFeatureResponseDTO(DeliveryOrderProjection deliveryOrderProjection, List<OrderHasProductResponseDTO> products, List<PaymentOrderResponseDTO> payments ){
        this.idOrder = deliveryOrderProjection.getId_order();
        this.idStoreShipping = deliveryOrderProjection.getId_store_shipping();
        this.idStoreReceive = deliveryOrderProjection.getId_store_receive();
        this.dateDeparture = deliveryOrderProjection.getDate_departure();
        this.dateEntry = deliveryOrderProjection.getDate_entry();
        this.estimatedDeliveryDate = deliveryOrderProjection.getEstimatedDeliveryDate();
        this.total = deliveryOrderProjection.getTotal();
        this.status = deliveryOrderProjection.getStatus();
        this.products = products;
        this.payments = payments;
    }
}
