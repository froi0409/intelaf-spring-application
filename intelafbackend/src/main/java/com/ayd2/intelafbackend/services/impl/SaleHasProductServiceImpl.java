package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.sale.reports.SaleHasProductReportResponseDTO;
import com.ayd2.intelafbackend.dto.sale.salehasproduct.SaleHasProductRequestDTO;
import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.entities.sales.SaleHasProduct;
import com.ayd2.intelafbackend.entities.sales.SaleHasProductPK;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.repositories.ProductRepository;
import com.ayd2.intelafbackend.repositories.SaleHasProductRepository;
import com.ayd2.intelafbackend.services.SaleHasProductService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleHasProductServiceImpl implements SaleHasProductService {

    private final SaleHasProductRepository saleHasProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleHasProductServiceImpl(SaleHasProductRepository saleHasProductRepository, ProductRepository productRepository) {
        this.saleHasProductRepository = saleHasProductRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void registerProduct(Sale sale, SaleHasProductRequestDTO saleHasProductRequestDTO) throws NotAcceptableException, EntityNotFoundException {
        Product product = productRepository.findById(saleHasProductRequestDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("product not found"));

        // Crear la clave primaria compuesta
        SaleHasProductPK pk = new SaleHasProductPK();
        pk.setSaleId(sale.getIdSale()); // Establecer el ID de la venta
        pk.setProductId(product.getIdProduct()); // Establecer el ID del producto

        SaleHasProduct newSaleHasProduct = new SaleHasProduct();
        newSaleHasProduct.setId(pk);
        newSaleHasProduct.setProduct(product);
        newSaleHasProduct.setSale(sale);
        newSaleHasProduct.setQuantity(saleHasProductRequestDTO.getQuantity());
        saleHasProductRepository.save(newSaleHasProduct);
    }
    
    @Override
    public List<SaleHasProductReportResponseDTO> findAllSalesByIdSale(Long idSale){
        return saleHasProductRepository.findAllBySale_IdSale(idSale)
                .stream()
                .map(SaleHasProductReportResponseDTO::new)
                .collect(Collectors.toList());
    }
}
