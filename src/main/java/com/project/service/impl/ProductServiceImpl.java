package com.project.service.impl;

import com.project.mapper.InventoryMapper;
import com.project.mapper.ProductMapper;
import com.project.pojo.dto.ProductCreatedDTO;
import com.project.pojo.entities.Product;
import com.project.pojo.entities.Stage;
import com.project.pojo.vo.ProductVO;
import com.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<ProductVO> getAllProducts() {
        return productMapper.getAll();
    }

    @Override
    public List<ProductVO> getProductByUserId(Integer id) {
        return productMapper.getProductsByUserId(id);
    }

    @Override
    public void createProduct(ProductCreatedDTO productCreatedDTO) {
        productMapper.insert(productCreatedDTO);
        inventoryMapper.initInventory(productCreatedDTO.getId());
    }

    @Override
    public void updateProductsStage(Integer id, Stage stage) {
        Product product = Product.builder()
                .id(id)
                .stage(stage)
                .build();

        productMapper.update(product);
    }

    @Override
    public void updateProduct(Integer id, String description, BigDecimal price) {
        Product product = Product.builder()
                .id(id)
                .description(description)
                .price(price)
                .build();

        productMapper.update(product);
    }
}
