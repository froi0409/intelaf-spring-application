/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.projectioninterface.order.reports;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author waliray
 */
public interface OrderByCustomerProjection {
    Long getId_order();
    String getId_store_shipping();
    String getId_store_receive();
    LocalDateTime getDate_departure();
    LocalDateTime getDate_entry();        
    BigDecimal getTotal();
    String getStatus();

    String getNit();
}
