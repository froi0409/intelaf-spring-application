/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.order.reports.OrderDetail;
import com.ayd2.intelafbackend.dto.order.reports.OrderReportResponseDTO;
import com.ayd2.intelafbackend.dto.products.reports.ProductBestSellingResponseDTO;
import com.ayd2.intelafbackend.dto.sale.reports.SaleByIdCustomerResponseDTO;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.services.OrderService;
import com.ayd2.intelafbackend.services.ProductService;
import com.ayd2.intelafbackend.services.SaleService;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasRole('EMPLOYEE')")
@RequestMapping("/v1/reports")
public class ReportsController {
    
    private final OrderService orderService;
    private final ProductService productService;
    private final SaleService saleService;
    
    
    @Autowired
    public ReportsController(OrderService orderService,ProductService productService, SaleService saleService) {
        this.orderService = orderService;
        this.productService = productService;
        this.saleService = saleService;
    }
    
    
    @GetMapping("/orders-by-idCustomer/{idCustomer}")
    public ResponseEntity<List<OrderReportResponseDTO>> orderByIdCustomer(@PathVariable Long idCustomer) {
        return ResponseEntity.ok(orderService.orderByIdCustomer(idCustomer));
    }
    
    @GetMapping("/sales-by-idCustomer/{idCustomer}")
    public ResponseEntity<List<SaleByIdCustomerResponseDTO>> salesByIdCustomer(@PathVariable Long idCustomer) {
        return ResponseEntity.ok(saleService.salesByIdCustomer(idCustomer));
    }
    
    @GetMapping("/best-selling-products")
    public ResponseEntity<List<ProductBestSellingResponseDTO>> getBestSellingProducts(
            @RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
            @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        
        return ResponseEntity.ok(productService.getBestSellingProducts(date1, date2));
    }

    @GetMapping("/orders-that-will-arrive/{idStore}")
    public ResponseEntity<List<OrderDetail>> getOrdersThatWillArrive(@PathVariable String idStore) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.reportPackagesThatWillArriveAtAStore(idStore));
    }
    
    
}
