package com.project.service;

import com.project.pojo.dto.OrderCreatedDTO;
import com.project.pojo.entities.Order;
import com.project.pojo.entities.OrderType;
import com.project.pojo.entities.OrderVVO;
import com.project.pojo.entities.Status;

import java.util.List;

public interface OrderService {
    List<OrderVVO> getAllOrders();

    List<OrderVVO> getOrderByUserId(Integer id);

    List<OrderVVO> getOrdersByProductId(Integer id);

    void createOrder(OrderCreatedDTO orderCreatedDTO);

    void updateOrder(Integer id, Status status, OrderType orderType);
}
