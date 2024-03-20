/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.products;

import com.ayd2.intelafbackend.entities.products.Product;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class ProductResponseDTO {
    private String id;
    private String name;
    private String manufacturer;
    private double price;
    private String description;
    private Integer guarantyMonths;

    // Constructor
    public ProductResponseDTO(Product productEntity) {
        this.id = productEntity.getIdProduct();
        this.name = productEntity.getName();
        this.manufacturer = productEntity.getManufacturer();
        this.price = productEntity.getPrice();
        this.description = productEntity.getDescription();
        this.guarantyMonths = productEntity.getGuarantyMonths();
    }
}
