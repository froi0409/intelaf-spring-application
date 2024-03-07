package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.repositories.SaleRepository;
import com.ayd2.intelafbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;

public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }



}
