/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.products.ProductRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreResponseDTO;
import java.util.List;

/**
 *
 * @author waliray
 */
public interface ProductService {
    
    ProductResponseDTO createProduct(ProductStoreRequestDTO newProduct);
    
    List<ProductResponseDTO> findAll();
    
    List<ProductStoreResponseDTO> findAllProductsWithStock();
    
    ProductResponseDTO updateProduct(String id, ProductStoreRequestDTO updatedProduct);
}
