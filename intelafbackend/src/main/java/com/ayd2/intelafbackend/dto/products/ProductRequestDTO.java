/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.products;

import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class ProductRequestDTO {
    final private String idProduct;
    final private String name;
    final private String manufacturer;
    final private double price;
    final private String description;
    final private Integer guarantyMonths;
}
