/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.products;

import com.ayd2.intelafbackend.entities.products.Product;
import java.util.List;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class ProductStoreResponseDTO {
    private String idProduct;
    private String name;
    private String manufacturer;
    private double price;
    private String description;
    private Integer guarantyMonths;
    private List<StoreProductStoreResponseDTO> stores;

    public ProductStoreResponseDTO(Product product, List<StoreProductStoreResponseDTO> stores) {
        this.idProduct = product.getIdProduct();
        this.name = product.getName();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.guarantyMonths = product.getGuarantyMonths();
        this.stores = stores;
    }
    
    
}
