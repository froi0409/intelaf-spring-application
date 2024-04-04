package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.sale.SaleOrderRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('EMPLOYEE')")
@RequestMapping(path = "/v1/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }


    @GetMapping("all")
    public ResponseEntity<List<SaleResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<SaleResponseDTO> registerSale(@RequestBody SaleRequestDTO saleRequestDTO) throws EntityNotFoundException, NotAcceptableException {
        SaleResponseDTO saleResponseDTO = saleService.registerSale(saleRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleResponseDTO);
    }

    @PostMapping("/register-order")
    public ResponseEntity<SaleResponseDTO> registerSaleFromOrder(@RequestBody SaleOrderRequestDTO saleRequestDTO) throws EntityNotFoundException, NotAcceptableException {
        SaleResponseDTO saleResponseDTO = saleService.registerSaleFromOrder(saleRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleResponseDTO);
    }

}
