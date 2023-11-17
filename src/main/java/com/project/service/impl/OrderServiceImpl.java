package com.project.service.impl;

import com.project.mapper.InventoryMapper;
import com.project.mapper.OrderMapper;
import com.project.mapper.ProductMapper;
import com.project.pojo.dto.InventoryDTO;
import com.project.pojo.dto.OrderCreatedDTO;
import com.project.pojo.entities.*;
import com.project.pojo.entities.OrderVVO;
import com.project.pojo.vo.OrderVO;
import com.project.pojo.vo.ProductVO;
import com.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<OrderVVO> getAllOrders() {
        List<OrderVVO> list = orderMapper.getAllOrders();
        return list;
    }

    @Override
    public List<OrderVVO> getOrderByUserId(Integer id) {
        List<OrderVVO> list = orderMapper.getOrderByUserId(id);
        return list;
    }

    @Override
    public List<OrderVVO> getOrdersByProductId(Integer id) {
        return orderMapper.getDoneNormalOrdersByProductId(id);
    }

    @Override
    public void createOrder(OrderCreatedDTO orderCreatedDTO) {
        Integer productId = orderCreatedDTO.getProductId();
        ProductVO product = productMapper.getProductById(productId);
        Inventory inventory = inventoryMapper.getInventoryByProductId(productId);
        if (product != null) {
            BigDecimal price = product.getPrice();
            BigDecimal count = new BigDecimal(orderCreatedDTO.getCount());
            BigDecimal totalPrice = price.multiply(count);
            orderCreatedDTO.setTotalAmount(totalPrice);
            orderCreatedDTO.setUserId(product.getUserId());
        } else {
            throw new RuntimeException("This product does not exist");
        }

        if (inventory != null) {
            if (orderCreatedDTO.getOrderType() == OrderType.Return && inventory.getDamagedGoodsCount() < orderCreatedDTO.getCount()) {
                throw new RuntimeException("The count of this product you want to return is more than damaged count");
            }
        }
        orderCreatedDTO.setStatus(Status.Created);
        orderMapper.createOrder(orderCreatedDTO);
    }

    @Override
    public void updateOrder(Integer id, Status status, OrderType orderType) {
        Order order = Order.builder()
                .id(id)
                .status(status)
                .build();
        OrderVO orderInDB = orderMapper.getOrderById(id);
        Integer productId = orderInDB.getProductId();
        if(status.equals(Status.Done) && orderType.equals(OrderType.Create)){
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setId(productId);
            Integer stock = inventoryMapper.getstock(productId);
            inventoryDTO.setStock(stock + orderInDB.getCount());
            inventoryMapper.updateCount(inventoryDTO);
        } else if (status.equals(Status.Done) && orderType.equals(OrderType.Return)) {
            Integer damageCount = inventoryMapper.getDamageCount(productId);
            Integer stock = inventoryMapper.getstock(productId);

            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setId(productId);
            inventoryDTO.setDamagedGoodsCount(damageCount - orderInDB.getCount());
            inventoryDTO.setStock(stock - orderInDB.getCount());
            inventoryMapper.updateCount(inventoryDTO);
        }
        order.setUpdatetime(LocalDateTime.now());
        orderMapper.updateOrder(order);
    }
}
