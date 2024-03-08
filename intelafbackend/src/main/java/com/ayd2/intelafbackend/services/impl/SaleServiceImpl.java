package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.sale.SaleRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.NotFoundException;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.repositories.SaleRepository;
import com.ayd2.intelafbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(SaleResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO registerSale(SaleRequestDTO saleRequestDTO) throws NotFoundException {

        Customer customer = customerRepository.findById(Long.valueOf(saleRequestDTO.getCustomer()))
                .orElseThrow(() -> new NotFoundException("customer not found"));

        Sale newSale = new Sale();
        newSale.setCustomer(customer);
        newSale.setDate(saleRequestDTO.getDate());
        newSale.setTotal(saleRequestDTO.getTotal());
        newSale = saleRepository.save(newSale);
        return  new SaleResponseDTO(newSale);
    }

}
