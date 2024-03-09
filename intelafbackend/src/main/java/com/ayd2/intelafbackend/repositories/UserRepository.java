package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
