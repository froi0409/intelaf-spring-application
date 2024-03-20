/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.products;

import java.util.List;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class ProductStoreRequestDTO {
    private String idProduct;
    private String name;
    private String manufacturer;
    private double price;
    private String description;
    private Integer guarantyMonths;
    private List<StoreProductStoreRequestDTO> stores;
}
