package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*find by nit in the table user*/
    @Query("SELECT c FROM Customer c JOIN c.user u WHERE u.nit = :nit")
    Optional<Customer> findByNit(@Param("nit") String nit);
}
