/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.products.ProductRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import java.util.List;

/**
 *
 * @author waliray
 */
public interface ProductService {
    
    ProductResponseDTO createProduct(ProductRequestDTO newProduct);
    
    List<ProductResponseDTO> findAll();
    
    ProductResponseDTO updateProduct(String id, ProductRequestDTO updatedProduct);
}
