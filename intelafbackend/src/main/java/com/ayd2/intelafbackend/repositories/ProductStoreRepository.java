/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.entities.products.ProductStore;
import com.ayd2.intelafbackend.entities.products.ProductStorePK;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author waliray
 */

public interface ProductStoreRepository extends CrudRepository<ProductStore, ProductStorePK>{
    
    List<ProductStore> findByIdProductIdProduct(String productIdProduct);


}
