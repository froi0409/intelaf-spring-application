/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.services.ProductService;
import com.ayd2.intelafbackend.dto.products.ProductRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author waliray
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO newProduct) {
        Product newProductEntity = new Product();
        newProductEntity.setIdProduct(newProduct.getIdProduct());
        newProductEntity.setName(newProduct.getName());
        newProductEntity.setManufacturer(newProduct.getManufacturer());
        newProductEntity.setPrice(newProduct.getPrice());
        newProductEntity.setDescription(newProduct.getDescription());
        newProductEntity.setGuarantyMonths(newProduct.getGuarantyMonths());
        
        newProductEntity = this.productRepository.save(newProductEntity);
        
        return new ProductResponseDTO(newProductEntity);
    }
    
    @Override
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()                
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public ProductResponseDTO updateProduct(String id, ProductRequestDTO updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(updatedProduct.getName());
            product.setManufacturer(updatedProduct.getManufacturer());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
            product.setGuarantyMonths(updatedProduct.getGuarantyMonths());
            
            product = productRepository.save(product);

            return new ProductResponseDTO(product);
        } else {
            return null;
        }
    }
}
