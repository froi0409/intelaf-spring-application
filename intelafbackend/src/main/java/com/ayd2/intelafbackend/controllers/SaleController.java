package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/v1/sale")
public class SaleController {

    @PostMapping("/register")
    public ResponseEntity<SaleResponseDTO> registerSAle(){
        SaleResponseDTO saleResponseDTO = new SaleResponseDTO();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleResponseDTO);
    }




}
