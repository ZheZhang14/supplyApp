package com.project.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryVO {
    private Integer id;
    private Integer productId;
    private Integer stock;
    private Integer expiredGoodsCount;
    private Integer damagedGoodsCount;
    private String valuation;
    private String productName;
}
