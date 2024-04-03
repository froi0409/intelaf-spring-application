/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.services.ProductService;
import com.ayd2.intelafbackend.dto.products.ProductRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreRequestDTO;
import com.ayd2.intelafbackend.dto.products.ProductStoreResponseDTO;
import com.ayd2.intelafbackend.dto.products.StoreProductStoreRequestDTO;
import com.ayd2.intelafbackend.dto.products.StoreProductStoreResponseDTO;
import com.ayd2.intelafbackend.dto.products.reports.ProductBestSellingResponseDTO;
import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.entities.products.ProductStore;
import com.ayd2.intelafbackend.entities.products.ProductStorePK;
import com.ayd2.intelafbackend.entities.store.Store;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.repositories.ProductRepository;
import com.ayd2.intelafbackend.repositories.ProductStoreRepository;
import com.ayd2.intelafbackend.repositories.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private ProductStoreRepository productStoreRepository;
    private StoreRepository storeRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,ProductStoreRepository productStoreRepository,StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.productStoreRepository = productStoreRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductStoreRequestDTO newProduct) throws DuplicatedEntityException{
        Optional<Product> isProductDuplicated = productRepository.findById(newProduct.getIdProduct());

        if (isProductDuplicated.isPresent()) {
            throw new DuplicatedEntityException(String.format("Product with code: %s, already exists. Try with other code", newProduct.getIdProduct()));
        }
        
        Product newProductEntity = new Product();
        newProductEntity.setIdProduct(newProduct.getIdProduct());
        newProductEntity.setName(newProduct.getName());
        newProductEntity.setManufacturer(newProduct.getManufacturer());
        newProductEntity.setPrice(newProduct.getPrice());
        newProductEntity.setDescription(newProduct.getDescription());
        newProductEntity.setGuarantyMonths(newProduct.getGuarantyMonths());
        
        newProductEntity = this.productRepository.save(newProductEntity);
        
        String errorEntityNotFoundStr = "";
        boolean errorEntityNotFound = false;
        
        for (StoreProductStoreRequestDTO storeDTO : newProduct.getStores()) {
            
            Optional<Store> store = this.storeRepository.findById(storeDTO.getStoreCode());
            
            if (store.isPresent()) {
                ProductStorePK idProductStore = new ProductStorePK(storeDTO.getStoreCode(),newProductEntity.getIdProduct());
                ProductStore productStoreEntity = new ProductStore();
                productStoreEntity.setId(idProductStore);
                productStoreEntity.setStock(storeDTO.getStock());
                this.productStoreRepository.save(productStoreEntity);
            }else{
                errorEntityNotFound = true;
                errorEntityNotFoundStr += "Store with ID: "+ storeDTO.getStoreCode() + " not found";
            }
        }
        if (errorEntityNotFound) {
            errorEntityNotFoundStr = "Product Saved but some stores not found: " + errorEntityNotFoundStr;
            throw new EntityNotFoundException(errorEntityNotFoundStr);
        }
        
        
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
    public List<ProductStoreResponseDTO> findAllProductsWithStock() {
        List<ProductStoreResponseDTO> productsResponse = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            List<StoreProductStoreResponseDTO> storesProductStoreResponseDTO= productStoreRepository.findByIdProductIdProduct(product.getIdProduct())
                .stream()                
                .map(StoreProductStoreResponseDTO::new)
                .collect(Collectors.toList());
            ProductStoreResponseDTO productResponseDTO = new ProductStoreResponseDTO(product,storesProductStoreResponseDTO);
            productsResponse.add(productResponseDTO);
        }
        
        return productsResponse;
    }
    
    @Override
    public ProductResponseDTO updateProduct(String id, ProductStoreRequestDTO updatedProduct) throws DuplicatedEntityException, EntityNotFoundException{        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with code: %s not found", updatedProduct.getIdProduct())));        
        product.setName(updatedProduct.getName());
        product.setManufacturer(updatedProduct.getManufacturer());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setGuarantyMonths(updatedProduct.getGuarantyMonths());
            
        product = productRepository.save(product);
        
        String errorEntityNotFoundStr = "";
        boolean errorEntityNotFound = false;
            
        for (StoreProductStoreRequestDTO storeDTO : updatedProduct.getStores()) {
            
            ProductStorePK idProductStore = new ProductStorePK(storeDTO.getStoreCode(),id);
                
            //Find the relation in db, if exists update else create
            Optional<ProductStore> optionalProductStore = productStoreRepository.findById(idProductStore);
            if (optionalProductStore.isPresent()) {
                ProductStore productStoreEntity = optionalProductStore.get();
                productStoreEntity.setStock(storeDTO.getStock());
                this.productStoreRepository.save(productStoreEntity);
            }else{
                Optional<Store> store = this.storeRepository.findById(idProductStore.getStoreIdStore());
                
                if (store.isPresent()) {
                    ProductStore productStoreEntity = new ProductStore();
                    productStoreEntity.setId(idProductStore);
                    productStoreEntity.setStock(storeDTO.getStock());      
                    this.productStoreRepository.save(productStoreEntity);
                }else{
                    errorEntityNotFound = true;
                    errorEntityNotFoundStr += "Store with ID: "+ idProductStore.getStoreIdStore() + " not found";
                }
            } 
        }
        if (errorEntityNotFound) {
            errorEntityNotFoundStr = "Product Saved but some stores not found: " + errorEntityNotFoundStr;
            throw new EntityNotFoundException(errorEntityNotFoundStr);
        }
        
        return new ProductResponseDTO(product);
    }
    
    @Override
    public ProductStoreResponseDTO findProductById(String id) throws EntityNotFoundException{
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<StoreProductStoreResponseDTO> stores = this.productStoreRepository.findByIdProductIdProduct(product.getIdProduct())
                    .stream()
                    .map(StoreProductStoreResponseDTO::new)
                    .collect(Collectors.toList());
            
            ProductStoreResponseDTO productStoreResponse = new ProductStoreResponseDTO(product,stores);
            
            return productStoreResponse;
        }else{
            throw new EntityNotFoundException(String.format("Product with code: %s, not found. Try with other code", id));
        }
    }

    @Override
    public void updateStock(String id, String storeCode, Integer quantity) throws EntityNotFoundException {
        String errorEntityNotFoundStr = "";
        boolean errorEntityNotFound = false;
        ProductStorePK idProductStore = new ProductStorePK(storeCode,id);
        Optional<ProductStore> optionalProductStore = productStoreRepository.findById(idProductStore);
        if (optionalProductStore.isPresent()) {
            ProductStore productStoreEntity = optionalProductStore.get();
            productStoreEntity.setStock(productStoreEntity.getStock() - quantity);
            this.productStoreRepository.save(productStoreEntity);
        } else {
            errorEntityNotFound = true;
            errorEntityNotFoundStr += "Store with ID: "+ idProductStore.getStoreIdStore() + " not found";
        }

        if (errorEntityNotFound) {
            throw new EntityNotFoundException(errorEntityNotFoundStr);
        }
    }
    
    @Override
    public List<ProductBestSellingResponseDTO> getBestSellingProducts(LocalDate date1,LocalDate date2){
        
        return productRepository.findBestSellingProducts(date1,date2)
                .stream()                
                .map(ProductBestSellingResponseDTO::new)
                .collect(Collectors.toList());        
    }
}
