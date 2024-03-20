/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.entities.products;

import com.ayd2.intelafbackend.entities.store.Store;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author waliray
 */
@Entity(name = "store_has_product")
@Getter
@Setter
@NoArgsConstructor
public class ProductStore {
    @EmbeddedId
    private ProductStorePK id;

    @ManyToOne
    @JoinColumn(name = "store_id_store", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id_product", insertable = false, updatable = false)
    private Product product;

    @Column(name = "stock")
    private Integer stock;
    
    
}
