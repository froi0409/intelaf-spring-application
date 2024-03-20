/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.products.ProductRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreResponseDTO;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author waliray
 */
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private ProductService productService;
    
    @Autowired
    public ProductController(ProductService ProductService) {
        this.productService = ProductService;
    } 
    
    @PostMapping("/create-product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductStoreRequestDTO newProduct) throws DuplicatedEntityException{
        ProductResponseDTO responseDTO = productService.createProduct(newProduct);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }
    
    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
    
    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<ProductStoreResponseDTO> findProductById(@PathVariable("id") String id) throws EntityNotFoundException{
        return ResponseEntity.ok(productService.findProductById(id));
    }
    
    @GetMapping("/get-all-products-stock")
    public ResponseEntity<List<ProductStoreResponseDTO>> findAllProductsWithStock() {
        return ResponseEntity.ok(productService.findAllProductsWithStock());
    }
     
    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") String id, @RequestBody ProductStoreRequestDTO updatedProduct) throws DuplicatedEntityException, EntityNotFoundException{
        ProductResponseDTO responseDTO = productService.updateProduct(id, updatedProduct);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
