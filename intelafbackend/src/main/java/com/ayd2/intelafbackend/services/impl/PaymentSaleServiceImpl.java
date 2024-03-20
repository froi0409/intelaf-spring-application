package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.sales.PaymentSale;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.repositories.PaymentSaleRepository;
import com.ayd2.intelafbackend.services.PaymentSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentSaleServiceImpl implements PaymentSaleService {

    private final PaymentSaleRepository paymentSaleRepository;

    @Autowired
    public PaymentSaleServiceImpl(PaymentSaleRepository paymentSaleRepository) {
        this.paymentSaleRepository = paymentSaleRepository;
    }

    @Override
    public void registerPayment(Sale sale, PaymentSaleResquestDTO paymentSaleResquestDTO) throws NotAcceptableException {

        PaymentSale newPayment = new PaymentSale();
        newPayment.setSale(sale);
        newPayment.setType(paymentSaleResquestDTO.getType());
        newPayment.setAmount(paymentSaleResquestDTO.getAmount());
        paymentSaleRepository.save(newPayment);
    }
}
