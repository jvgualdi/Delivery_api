package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.dto.ProductOrderDTO;
import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.model.Product;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.CustomerRepository;
import com.jvgualdi.deliveryapi.repository.ProductOrderRepository;
import com.jvgualdi.deliveryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderService {

    @Autowired
    public ProductOrderRepository productOrderRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public ProductRepository productRepository;

    public void save(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setStatus(productOrderDTO.getStatus());
        productOrder.setDescription(productOrderDTO.getDescription());
        productOrder.setMomentRequested(LocalDateTime.now());
        productOrder.setProducts(productOrderDTO.getProducts());
        productOrder.setCustomer(productOrderDTO.getCustomer());

        List<Product> products = new ArrayList<>();
        productOrderDTO.getProducts().forEach(product -> products.add(productRepository.findById(product.getId()).get()));

        if (productOrder.getSubTotal() == 0){
            double productSum = products.stream().mapToDouble((Product::getPrice)).sum();
            productOrder.setSubTotal(productSum);
        }else{
            productOrder.setSubTotal(productOrderDTO.getSubTotal());
            productOrder.setTotal(productOrderDTO.getTotal());
        }

        productOrderRepository.save(productOrder);
    }

//    public void save(ProductOrderDTO productOrderDTO, Integer customerID) {
//        ProductOrder productOrder = new ProductOrder();
//
//        productOrder.setStatus(productOrderDTO.getStatus());
//        productOrder.setDescription(productOrderDTO.getDescription());
//        productOrder.setMomentRequested(LocalDateTime.now());
//        productOrder.setProducts(productOrderDTO.getProducts());
//
//        List<Product> products = new ArrayList<>();
//        productOrderDTO.getProducts().forEach(product -> products.add(productRepository.findById(product.getId()).get()));
//
//        if (productOrder.getSubTotal() == 0){
//            double productSum = products.stream().mapToDouble((product -> product.getPrice())).sum();
//            productOrder.setSubTotal(productSum);
////            if (productOrderDTO.getDelivery() != null){
////                productOrder.setTotal(productSum + productOrderDTO.getDelivery().getTax());
////            }
//        }else{
//            productOrder.setSubTotal(productOrderDTO.getSubTotal());
//            productOrder.setTotal(productOrderDTO.getTotal());
//        }
//
//        Customer customer = customerRepository.findById(customerID).orElse(null);
//        if (customer != null) {
//            productOrder.setCustomer(customer);
//        }
//
//        productOrderRepository.save(productOrder);
//    }

    public void update(ProductOrder productOrder, ProductOrderDTO productOrderDTO) {
        productOrder.setStatus(productOrderDTO.getStatus());
        productOrder.setTotal(productOrderDTO.getTotal());
        productOrder.setSubTotal(productOrderDTO.getSubTotal());
        productOrder.setDescription(productOrderDTO.getDescription());

        productOrderRepository.save(productOrder);
    }


}
