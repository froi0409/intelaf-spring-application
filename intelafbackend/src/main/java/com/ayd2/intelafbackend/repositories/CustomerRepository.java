package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.users.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}