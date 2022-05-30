package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.dto.ProductDTO;
import com.jvgualdi.deliveryapi.model.Product;
import com.jvgualdi.deliveryapi.repository.ProductRepository;
import com.jvgualdi.deliveryapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public ProductService productService;


    @PostMapping("/register")
    public void register (@RequestBody Product product){
        productRepository.save(product);
    }

    @GetMapping("/list")
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @DeleteMapping("/delete/{productID}")
    public void delete (@PathVariable("productID") Integer productID){
        productRepository.deleteById(productID);
    }

    @PutMapping("/update/{productID}")
    public void update (@PathVariable("productID") Integer productID, @RequestBody ProductDTO productDTO) throws Exception {
        Product product = productRepository.findById(productID).orElse(null);
        if (product != null) {
            productService.update(product, productDTO);
        }else {
            throw new Exception("Delivery not found");
        }
    }

}
