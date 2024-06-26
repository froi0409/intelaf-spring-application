package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.sales.Sale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByCustomerUserIdUser(Long userIdUser);
    
}
