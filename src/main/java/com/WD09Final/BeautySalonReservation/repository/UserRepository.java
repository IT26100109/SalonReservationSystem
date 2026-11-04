package com.WD09Final.BeautySalonReservation.repository;
import com.WD09Final.BeautySalonReservation.entities.Customer;
import com.WD09Final.BeautySalonReservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();
}