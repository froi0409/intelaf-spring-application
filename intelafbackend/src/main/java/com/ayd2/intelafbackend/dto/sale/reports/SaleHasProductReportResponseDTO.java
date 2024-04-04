/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.sale.reports;

import com.ayd2.intelafbackend.entities.sales.SaleHasProduct;
import com.ayd2.intelafbackend.projectioninterface.sale.reports.SaleHasProductReportProjection;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class SaleHasProductReportResponseDTO {
    String productId;
    Integer quantity;
    String name;
    Double price;

    public SaleHasProductReportResponseDTO(SaleHasProduct saleHasProduct) {
        this.productId = saleHasProduct.getProduct().getIdProduct();
        this.quantity = saleHasProduct.getQuantity();
        this.name = saleHasProduct.getProduct().getName();
        this.price = saleHasProduct.getProduct().getPrice();
    }
    
    
}
