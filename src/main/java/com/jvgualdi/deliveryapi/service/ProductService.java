package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.dto.ProductDTO;
import com.jvgualdi.deliveryapi.model.Product;
import com.jvgualdi.deliveryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public void update(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        productRepository.save(product);
    }
}
