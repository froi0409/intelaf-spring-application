package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.sale.SaleRequestDTO;
import com.ayd2.intelafbackend.dto.sale.SaleResponseDTO;
import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.dto.sale.salehasproduct.SaleHasProductRequestDTO;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.repositories.SaleRepository;
import com.ayd2.intelafbackend.services.PaymentSaleService;
import com.ayd2.intelafbackend.services.SaleHasProductService;
import com.ayd2.intelafbackend.services.SaleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final PaymentSaleService paymentSaleService;
    private final SaleHasProductService saleHasProductService;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, PaymentSaleService paymentSaleService, SaleHasProductService saleHasProductService) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.paymentSaleService = paymentSaleService;
        this.saleHasProductService = saleHasProductService;
    }


    @Override
    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(SaleResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public SaleResponseDTO registerSale(SaleRequestDTO saleRequestDTO) throws EntityNotFoundException, NotAcceptableException {

        Customer customer = customerRepository.findByNit(saleRequestDTO.getNit())
                .orElseThrow(() -> new EntityNotFoundException("customer not found"));

        Sale newSale = new Sale();
        newSale.setCustomer(customer);
        newSale.setDate(saleRequestDTO.getDate());
        newSale.setTotal(saleRequestDTO.getTotal());
        newSale = saleRepository.save(newSale);
         //Add payment_sale
        BigDecimal usedCredit = BigDecimal.valueOf(0);
        for (PaymentSaleResquestDTO paymentRequestDTO : saleRequestDTO.getPayments()) {
            paymentSaleService.registerPayment(newSale, paymentRequestDTO);
            if (paymentRequestDTO.getType().equalsIgnoreCase("credit")) {
                usedCredit = usedCredit.add(BigDecimal.valueOf(paymentRequestDTO.getAmount()));
            }
        }
        //UPDATE THE CREDITS IF WAS NECESARY
        // UPDATE CREDITS
        if (usedCredit.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal newCredits = customer.getCredit().subtract(usedCredit);
            //  actualizar la entidad 'customer'
            customer.setCredit(newCredits);
            // 'customer' tiene el nuevo crédito actualizado
            customerRepository.save(customer);
        }
        //Add sale_has_product
        for (SaleHasProductRequestDTO saleHasProductRequestDTO : saleRequestDTO.getProducts()) {
            saleHasProductService.registerProduct(newSale, saleHasProductRequestDTO);
        }
        //CHANGE THE STOCK FOR THE PRODUCTS

        return  new SaleResponseDTO(newSale);
    }

}
