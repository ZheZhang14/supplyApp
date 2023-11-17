package com.project.service.impl;

import com.project.mapper.InventoryMapper;
import com.project.mapper.OrderMapper;
import com.project.pojo.dto.InventoryDTO;
import com.project.pojo.dto.InventoryVO;
import com.project.pojo.entities.Inventory;
import com.project.pojo.entities.Order;
import com.project.pojo.entities.Status;
import com.project.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<InventoryVO> getAllInventory() {
        return inventoryMapper.getAll();
    }

    @Override
    public void updateInventory(InventoryDTO inventoryDTO) {
        inventoryMapper.updateCount(inventoryDTO);
    }
}
