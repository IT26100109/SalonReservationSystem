package com.WD09Final.BeautySalonReservation.repository;


import com.WD09Final.BeautySalonReservation.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByQuantityGreaterThan(int quantity);
}