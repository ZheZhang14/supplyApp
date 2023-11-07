package com.scu.supply.api;

import com.scu.supply.entities.Product;
import com.scu.supply.entities.User;
import com.scu.supply.entities.UserType;
import com.scu.supply.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @PostMapping("/post/testsignin")
    public ResponseEntity<?> signin(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        System.out.println(allParams);

        if (allParams.get("username").equals("admin")) {
            User u = new User();
            u.setROLE(UserType.ADMIN);
            u.setId(1);
            u.setUsername(allParams.get("username"));
            return ResponseEntity.ok().body(u);
        } else if (allParams.get("username").equals("supplier")) {
            User u = new User();
            u.setROLE(UserType.SUPPLIER);
            u.setId(2);
            u.setUsername(allParams.get("username"));
            return ResponseEntity.ok().body(u);
        } else if (allParams.get("username").equals("distributor")) {
            User u = new User();
            u.setROLE(UserType.DISTRIBUTOR);
            u.setId(3);
            u.setUsername(allParams.get("username"));
            return ResponseEntity.ok().body(u);
        } else if (allParams.get("username").equals("mac")) {
            User u = new User();
            u.setROLE(UserType.MANUFACTURER);
            u.setId(4);
            u.setUsername(allParams.get("username"));
            return ResponseEntity.ok().body(u);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
    }

    @PostMapping("/post/testsignup")
    public ResponseEntity<String> signup(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        System.out.println(allParams);
        if (allParams.get("username").equals("admin")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        } else {
            return ResponseEntity.ok().body("Successfully registered!");
        }
    }

    @PostMapping("/post/testedit")
    public ResponseEntity<String> editUser(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        System.out.println(allParams);
        if (allParams.get("username").equals("admin")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        } else {
            return ResponseEntity.ok().body("Successfully edited profile!");
        }
    }
}



