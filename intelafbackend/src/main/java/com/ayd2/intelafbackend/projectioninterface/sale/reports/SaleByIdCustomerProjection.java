/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.projectioninterface.sale.reports;

import java.time.LocalDateTime;

/**
 *
 * @author waliray
 */
public interface SaleByIdCustomerProjection {
    Long getIdSale();
    LocalDateTime getDate();
    Double getTotal();
    String getNit();    
}
