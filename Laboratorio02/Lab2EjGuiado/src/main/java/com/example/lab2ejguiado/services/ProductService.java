package com.example.lab2ejguiado.services;


import com.example.lab2ejguiado.domain.entity.Product;

import java.util.UUID;

public interface ProductService {
    void createProduct(Product product);
    Product getProduct(String name);
    void updateProduct(UUID id, Product product);
    Product deleteProduct(UUID id);
}