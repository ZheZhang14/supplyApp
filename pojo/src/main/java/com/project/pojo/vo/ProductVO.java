package com.project.pojo.vo;

import com.project.pojo.entities.Stage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
    private Integer id;
    private String productName;
    private String description;
    private BigDecimal price;
    private Stage stage;
    private Integer userId;
    private String contactName;
}
