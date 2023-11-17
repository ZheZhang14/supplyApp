package com.project.pojo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderVVO {
    private Integer id;
    private String productName;
    private Integer count;
    private BigDecimal totalAmount;
    private String contactName;
    private OrderType orderType;
    private Status status;
    private BigDecimal price;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
}
