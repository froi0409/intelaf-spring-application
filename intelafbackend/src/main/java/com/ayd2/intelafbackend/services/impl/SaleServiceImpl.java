package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.repositories.SaleRepository;
import com.ayd2.intelafbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(SaleResponseDTO::new)
                .collect(Collectors.toList());
    }
}
