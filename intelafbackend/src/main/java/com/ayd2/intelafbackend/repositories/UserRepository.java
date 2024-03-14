package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.nit = :nit OR u.dpi = :dpi OR u.username = :username")
    Optional<User> findByNitDPIUsername(@Param("nit") String nit, @Param("dpi") String dpi, @Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.nit = :nit")
    Optional<User> findByNit(@Param("nit") String nit);

    @Query("SELECT u FROM User u WHERE (u.dpi = :dpi OR u.username = :username) AND u.nit <> :nit")
    Optional<User> findDPIUsernameDiferentByNit(@Param("nit") String nit, @Param("dpi") String dpi, @Param("username") String username);
}
