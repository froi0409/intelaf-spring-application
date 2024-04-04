/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.products.reports;

import com.ayd2.intelafbackend.projectioninterface.order.product.ProductBestSellingProjection;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class ProductBestSellingResponseDTO {
    private String id;
    private String name;
    private String manufacturer;
    private double price;
    private String description;
    private Integer guarantyMonths;
    private double quantity;

    public ProductBestSellingResponseDTO(ProductBestSellingProjection productEntity) {
        this.id = productEntity.getId_product();
        this.name = productEntity.getName();
        this.manufacturer = productEntity.getManufacturer();
        this.price = productEntity.getPrice();
        this.description = productEntity.getDescription();
        this.guarantyMonths = productEntity.getGuaranty_months();
        this.quantity = productEntity.getQuantity();
    }
    
    
}
