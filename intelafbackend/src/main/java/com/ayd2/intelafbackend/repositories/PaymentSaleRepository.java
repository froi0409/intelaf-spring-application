package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.sales.PaymentSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSaleRepository extends JpaRepository<PaymentSale, Long > {
}
