package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
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

//    @PostMapping("/register")
//    public ResponseEntity<SaleResponseDTO> registerSAle(){
//        SaleResponseDTO saleResponseDTO = new SaleResponseDTO(null);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(saleResponseDTO);
//    }




}
