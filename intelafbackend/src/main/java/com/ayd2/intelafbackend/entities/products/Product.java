/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.entities.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author waliray
 */
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    
    @Id
    @Column
    private String idProduct;
    
    @Column
    private String name;
    
    @Column
    private String manufacturer;
    
    @Column
    private double price;
    
    @Column
    private String description;
    
    @Column
    private Integer guarantyMonths;
    
    
}
