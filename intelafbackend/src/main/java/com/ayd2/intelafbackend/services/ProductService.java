/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.products.ProductRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreResponseDTO;
import com.ayd2.intelafbackend.dto.products.reports.ProductBestSellingResponseDTO;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author waliray
 */
public interface ProductService {
    
    ProductResponseDTO createProduct(ProductStoreRequestDTO newProduct) throws DuplicatedEntityException;
    
    List<ProductResponseDTO> findAll();
    
    List<ProductStoreResponseDTO> findAllProductsWithStock();
    
    ProductResponseDTO updateProduct(String id, ProductStoreRequestDTO updatedProduct) throws DuplicatedEntityException, EntityNotFoundException;
    
    ProductStoreResponseDTO findProductById(String id) throws EntityNotFoundException;

    void updateStock(String id, String storeCode, Integer quantity ) throws EntityNotFoundException;
    
    List<ProductBestSellingResponseDTO> getBestSellingProducts(LocalDate date1,LocalDate date2);
}
