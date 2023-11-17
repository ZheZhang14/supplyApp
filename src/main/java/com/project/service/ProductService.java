package com.project.service;

import com.project.pojo.dto.ProductCreatedDTO;
import com.project.pojo.entities.Product;
import com.project.pojo.entities.Stage;
import com.project.pojo.vo.ProductVO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductVO> getAllProducts();

    List<ProductVO> getProductByUserId(Integer id);

    void createProduct(ProductCreatedDTO productCreatedDTO);

    void updateProductsStage(Integer id, Stage stage);

    void updateProduct(Integer id, String description, BigDecimal price);
}
