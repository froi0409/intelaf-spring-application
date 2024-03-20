/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.entities.products;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author waliray
 */
@Embeddable
@Getter
@Setter
public class ProductStorePK implements Serializable{
    @Column(name = "store_id_store", length = 10)
    private String storeIdStore;

    @Column(name = "product_id_product", length = 10)
    private String productIdProduct;    

    public ProductStorePK() {
    }

    public ProductStorePK(String storeIdStore, String productIdProduct) {
        this.storeIdStore = storeIdStore;
        this.productIdProduct = productIdProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStorePK)) return false;
        ProductStorePK that = (ProductStorePK) o;
        return Objects.equals(storeIdStore, that.storeIdStore) &&
                Objects.equals(productIdProduct, that.productIdProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeIdStore, productIdProduct);
    }
}
