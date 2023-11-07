package com.scu.supply.api;

import com.scu.supply.entities.*;
import com.scu.supply.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v2")
public class MainController {

    @Autowired
    private ProductRepo productRepo;

    /**
     * User related, including sign in, signup, edit info, get all users, get user by id
     */
    @PostMapping("/users/signin")
    public ResponseEntity<?> signin(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        // allParams will be like {"username": "xxxx", "password": "xxxxxxxxx"};
        boolean found = true;
        // check users table, if found the user, return the User. for ex like below
        if (found) {
            User u = new User();
            u.setROLE(UserType.ADMIN);
            u.setId(1);
            u.setUsername(allParams.get("username"));
            return ResponseEntity.ok().body(u);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
    }

    @PostMapping("/users/signup")
    public ResponseEntity<?> signup(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        // allParams will be like {"username": "xxxx", "password": "xxxxxxxxx", "role": "ADMIN", "name": "XXXX", .............};
        // refer mockdata/user1.json
        boolean everythingLooksGood = true;
        if (everythingLooksGood) {
            // insert a new row in User table
            return ResponseEntity.ok().body("Successfully registered!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        }
    }

    @PostMapping("/users/{id}/edit")
    public ResponseEntity<String> editUser(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        // allParams will be like {"username": "xxxx", "password": "xxxxxxxxx", "role": "ADMIN", "name": "XXXX", .............};
        // refer mockdata/user1.json
        boolean everythingLooksGood = true;
        if (everythingLooksGood) {
            // update the row in User table
            return ResponseEntity.ok().body("Successfully updated!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        // return all Users in users table
        return ResponseEntity.ok().body(new ArrayList<User>());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
        // return User by Id
        return ResponseEntity.ok().body(new User());
    }

    /**
     * Overview, including get overview of all users, get self overview
     */
    @GetMapping("/overview")
    public ResponseEntity<Overview> getOverview() {
        // return overview of all users orders and products count
        return ResponseEntity.ok().body(new Overview());
    }

    @GetMapping("/overview/{id}")
    public ResponseEntity<Overview> getOverviewByUserId(@PathVariable(value = "id") Long userId) {
        // return overview of orders and products count by userId
        // don't need users count in this function
        // for a vendor(user), he can only view his own orders and products info
        Overview o = new Overview();
        o.setOrdersCount(new ArrayList<>());
        o.setProductsCount(new ArrayList<>());
        return ResponseEntity.ok().body(o);
    }

    /**
     * Product related, including get all products, get self products(products provided by the vendor)
     * create product and product stage change
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productRepo.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<Product>> getProductByUserId(@PathVariable(value = "id") Long userId)
            throws RuntimeException {
        // return product by user Id
        return ResponseEntity.ok().body(new ArrayList<Product>());
    }

    @PostMapping("/products/create")
    public ResponseEntity<?> createProduct(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        // allParams will be like {"productName": "xxxx", "price": xx, "vendorId"(or you can say userId): "xxxx"};
        // refer mockdata/products.json
        boolean everythingLooksGood = true;
        if (everythingLooksGood) {
            // insert a new row in Product table
            // newly created product's stage is offMarket
            return ResponseEntity.ok().body("Successfully created Order!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        }
    }

    @PostMapping("/products/{id}/update/stage")
    public ResponseEntity<?> updateProductStage(@PathVariable(value = "id") Long productId)
            throws RuntimeException {
        boolean found = true;
        // if product id found
        if (found) {
            // update product stage from onMarket to offMarket or from offMarket to onMarket
            // depending on current stage
            return ResponseEntity.ok().body("Successfully created Order!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product not found!");
        }
    }

    /**
     * Order related, including get all orders, get self orders(orders to the vendor)
     * create order and update order status
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        // return all orders
        return ResponseEntity.ok().body(new ArrayList<Order>());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable(value = "id") Long userId)
            throws RuntimeException {
        // return orders by userId
        return ResponseEntity.ok().body(new ArrayList<Order>());
    }

    @PostMapping("/orders/create")
    public ResponseEntity<?> createOrder(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        // allParams will be like {"productId": "xxxx", "count": xx, "vendorId"(or you can say userId): "xxxx", "orderTyep": "return or normal"};
        // refer mockdata/orders.json
        boolean everythingLooksGood = true;
        if (everythingLooksGood) {
            // insert a new row in Order table
            return ResponseEntity.ok().body("Successfully created Order!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        }
    }

    @PostMapping("/orders/update/status")
    public ResponseEntity<?> updateOrder(@RequestParam Map<String,String> allParams)
            throws RuntimeException {
        // allParams will be like {"orderId": "xxxx", "status": xx};
        boolean everythingLooksGood = true;
        if (everythingLooksGood) {
            // update order status
            return ResponseEntity.ok().body("Successfully created Order!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong!");
        }
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getInventory() {
        // return inventory
        return ResponseEntity.ok().body(new ArrayList<Inventory>());
    }
}