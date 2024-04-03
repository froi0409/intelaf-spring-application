package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.sales.SaleHasProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleHasProductRepository extends JpaRepository<SaleHasProduct, Long> {
    
    List<SaleHasProduct> findAllBySale_IdSale(Long idSale);
}
