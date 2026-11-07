package com.WD09Final.BeautySalonReservation.service;


import com.WD09Final.BeautySalonReservation.dto.ProductForm;
import com.WD09Final.BeautySalonReservation.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getAvailableProducts();
    Product getProductById(Long id);
    Product createProduct(ProductForm form);
    Product updateProduct(Long id, ProductForm form);
    void deleteProduct(Long id);
    ProductForm toForm(Product product);

    // Purchase method to decrement stock quantity
    void purchaseProduct(Long id, int quantity);
}