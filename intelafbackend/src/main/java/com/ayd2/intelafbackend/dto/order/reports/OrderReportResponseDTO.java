/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.order.reports;

import com.ayd2.intelafbackend.dto.order.orderhasproducts.OrderHasProductResponseDTO;
import com.ayd2.intelafbackend.dto.order.paymentorder.PaymentOrderResponseDTO;
import com.ayd2.intelafbackend.projectioninterface.order.DeliveryOrderProjection;
import com.ayd2.intelafbackend.projectioninterface.order.reports.OrderByCustomerProjection;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class OrderReportResponseDTO {
    Long idOrder;
    String idStoreShipping;
    String idStoreReceive;
    LocalDateTime dateDeparture;
    LocalDateTime dateEntry;    
    BigDecimal total;
    String status;
    String nit;
    List<OrderHasProductResponseDTO> products;
    List<PaymentOrderResponseDTO> payments;


    public OrderReportResponseDTO(OrderByCustomerProjection deliveryOrderProjection, List<OrderHasProductResponseDTO> products, List<PaymentOrderResponseDTO> payments ){
        this.idOrder = deliveryOrderProjection.getId_order();
        this.idStoreShipping = deliveryOrderProjection.getId_store_shipping();
        this.idStoreReceive = deliveryOrderProjection.getId_store_receive();
        this.dateDeparture = deliveryOrderProjection.getDate_departure();
        this.dateEntry = deliveryOrderProjection.getDate_entry();        
        this.total = deliveryOrderProjection.getTotal();
        this.status = deliveryOrderProjection.getStatus();
        this.nit = deliveryOrderProjection.getNit();
        this.products = products;
        this.payments = payments;
    }
}
