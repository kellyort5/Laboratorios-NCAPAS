package com.example.lab2ejguiado.services.impl;

import com.example.lab2ejguiado.domain.entity.Product;
import com.example.lab2ejguiado.repository.ProductRepository;
import com.example.lab2ejguiado.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }
    @Override
    public Product getProduct(String name) {
        return productRepository.getProductsByName(name);
    }
    @Override
    public void updateProduct(UUID id, Product product) {
        Product existProduct = productRepository.getProductsById(id);
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        existProduct.setAvailable(product.getAvailable());
        productRepository.save(existProduct);
    }
    @Override
    public Product deleteProduct(UUID id) {
        Product existProduct = productRepository.getProductsById(id);
        productRepository.deleteById(id);
        return existProduct;
    }
}

