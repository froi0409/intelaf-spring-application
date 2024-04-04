/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.projectioninterface.sale.reports;

/**
 *
 * @author waliray
 */
public interface SaleHasProductReportProjection {
    String getProductId();
    Integer getQuantity();
    String getName();
    Double getPrice();
}
