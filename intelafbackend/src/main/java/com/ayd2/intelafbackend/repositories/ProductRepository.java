/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.products.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author waliray
 */
public interface ProductRepository extends CrudRepository<Product, String>{
    @Override
    List<Product> findAll();
        
    @Override
    Optional<Product> findById(String id);
}
