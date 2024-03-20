/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.products;

import com.ayd2.intelafbackend.entities.products.ProductStore;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class StoreProductStoreResponseDTO {
    private String storeCode;
    private int stock; 

    public StoreProductStoreResponseDTO(ProductStore productStore) {
        this.storeCode = productStore.getStore().getIdStore();
        this.stock = productStore.getStock();
    }
}
