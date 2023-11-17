package com.project.controller.api;

import com.project.constant.JwtClaimsConstant;
import com.project.context.BaseContext;
import com.project.exception.AccountNotFoundException;
import com.project.exception.PasswordErrorException;
import com.project.pojo.dto.*;
import com.project.pojo.entities.*;
import com.project.pojo.vo.*;
import com.project.properties.JwtProperties;
import com.project.result.Result;
import com.project.service.InventoryService;
import com.project.service.OrderService;
import com.project.service.ProductService;
import com.project.service.UserService;
import com.project.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * User related, including sign in, signup, edit info, get all users, get user by id
     */
    @PostMapping("/users/signin")
    public Result<?> signin(@RequestParam Map<String,String> allParams) {
        UserLoginDTO dto = new UserLoginDTO();
        dto.setUsername(allParams.get("username"));
        dto.setPassword(allParams.get("password"));
        User user;
        try {
            user = userService.login(dto);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .name(user.getContactName())
                .userType(user.getUserRole())
                .build();
        return Result.success(userLoginVO);
    }

    @PostMapping("/users/signup")
    public Result signup(@RequestParam Map<String,String> allParams) throws Exception {
        User newUser = new User();
        newUser.setUsername(allParams.get("username"));
        newUser.setPassword(allParams.get("password"));
        newUser.setUserRole(UserType.valueOf(allParams.get("usertype")));
        newUser.setEmail(allParams.get("email"));
        newUser.setPhone(allParams.get("phone"));
        newUser.setAddress(allParams.get("address"));
        newUser.setContactName(allParams.get("name"));
        newUser.setImagePath("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        newUser.setDateCreated(LocalDateTime.now());
        userService.register(newUser);
        return Result.success();
    }


    @PostMapping("/users/{id}/edit")
    public Result editUser(@PathVariable(value = "id") Integer userId, @RequestParam Map<String,String> allParams) throws Exception {
        UserEditDTO dto = new UserEditDTO();
        dto.setId(userId);
        dto.setUsername(allParams.get("username"));
        dto.setPassword(allParams.get("password"));
        dto.setUserRole(UserType.valueOf(allParams.get("usertype")));
        dto.setEmail(allParams.get("email"));
        dto.setPhone(allParams.get("phone"));
        dto.setAddress(allParams.get("address"));
        dto.setContactName(allParams.get("name"));
        dto.setImagePath(allParams.get("imagePath"));

        userService.update(dto);
        return Result.success();
    }


    @GetMapping("/users")
    public Result<List<UserVO>> getAllUsers() {
        System.out.println("getAllUsers");
        List<UserVO> list = userService.list();
        return Result.success(list);
    }

    @GetMapping("/users/{userId}")
    public Result<UserByIdVO> getUserById(@PathVariable Integer userId) {
        UserByIdVO userById = userService.getUserById(userId);
        return Result.success(userById);
    }



    /**
     * Overview, including get overview of all users, get self overview
     */
    @GetMapping("/overview")
    public Result<OverviewVO> getOverview(){
       OverviewVO overviewVO = userService.getOverview();
        return Result.success(overviewVO);
    }

    @GetMapping("/overview/{id}")
    public Result<OverviewVO> getOverviewByUserId(@PathVariable Integer id) {
        OverviewVO overviewVO = userService.getOverviewByUserId(id);
        return Result.success(overviewVO);
    }

    /**
     * Product related, including get all products, get self products(products provided by the vendor)
     * create product and product stage change
     */
    @GetMapping("/products")
    public Result<List<Product>> getAllProducts() {
        return Result.success(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public Result<Product> getProductByUserId(@PathVariable(value = "id") Integer id){
       return Result.success(productService.getProductByUserId(id));
    }

    @PostMapping("/products/create")
    public Result createProduct(@RequestBody ProductCreatedDTO productCreatedDTO){
        productCreatedDTO.setUserId(BaseContext.getCurrentId());
        productService.createProduct(productCreatedDTO);
        return Result.success();
    }

    @PostMapping("/products/{id}/update/stage")
    public Result<String> updateProductStage(@PathVariable(value = "id") Integer id, Stage stage){
        productService.updateProductsStage(id,stage);
        return Result.success();
    }

    /**
     * Order related, including get all orders, get self orders(orders to the vendor)
     * create order and update order status
     */
    @GetMapping("/orders")
    public Result<List<Order>> getAllOrders() {
        List<Order> list = orderService.getAllOrders();
        return Result.success(list);
    }

    @GetMapping("/orders/{id}")
    public Result <List<Order>> getOrderByUserId(@PathVariable(value = "id") Integer id){
       List<Order> list = orderService.getOrderByUserId(id);
        return Result.success(list);
    }

    @PostMapping("/orders/create")
    public Result createOrder(@RequestBody OrderCreatedDTO orderCreatedDTO){
        orderCreatedDTO.setUserId(BaseContext.getCurrentId());
        orderService.createOrder(orderCreatedDTO);
        return Result.success();
    }

    @PostMapping("/orders/{id}/update/status")
    public Result<String> updateOrder(@PathVariable(value="id") Integer id, Status status){
        orderService.updateOrder(id,status);
        return Result.success();
    }

    @GetMapping("/inventory")
    public Result<List<Inventory>> getInventory(){
        List<Inventory> list = inventoryService.getAllInventory();
        return Result.success(list);
    }

    @PostMapping("/inventory/{id}/update")
    public Result<String> updateInventory(@PathVariable(value="id") Integer id, @RequestBody InventoryDTO inventoryDTO){
        inventoryService.updateInventory(id,inventoryDTO);
        return Result.success();
    }
}