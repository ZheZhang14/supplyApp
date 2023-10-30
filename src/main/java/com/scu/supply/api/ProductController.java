package com.scu.supply.api;

import com.scu.supply.entities.Product;
import com.scu.supply.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/helloworld")
    public String hello() {
        return"Hello World!";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws RuntimeException {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }
}



