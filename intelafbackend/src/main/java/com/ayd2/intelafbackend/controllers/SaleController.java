package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.sale.SaleRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.exceptions.NotFoundException;
import com.ayd2.intelafbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/v1/sale")
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend origin
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
    public ResponseEntity<SaleResponseDTO> registerSale(@RequestBody SaleRequestDTO saleRequestDTO) throws NotFoundException {
        SaleResponseDTO saleResponseDTO = saleService.registerSale(saleRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleResponseDTO);
    }




}
